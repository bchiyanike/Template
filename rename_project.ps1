param (
    [Parameter(Mandatory=$true)]
    [string]$NewName,
    [Parameter(Mandatory=$true)]
    [string]$AppName,
    [string]$OldName = "template",
    [string]$OldAppName = "Template"
)

# Convert names to TitleCase for Theme renaming
$TextInfo = (Get-Culture).TextInfo
$NewTitleName = $TextInfo.ToTitleCase($NewName).Replace(" ", "")
$OldTitleName = $TextInfo.ToTitleCase($OldName).Replace(" ", "")

Write-Host "=== Renaming project from $OldName to $NewName ===" -ForegroundColor Cyan

# 1. Replace strings in files
$excludeDirs = @(".git", ".gradle", ".idea", "build")
$files = Get-ChildItem -Path . -Recurse -File | Where-Object {
    $path = $_.FullName
    $match = $false
    foreach ($dir in $excludeDirs) {
        if ($path -like "*\$dir\*") { $match = $true; break }
    }
    !$match -and ($_.Extension -in ".kt", ".xml", ".kts", ".json", ".pro")
}

foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    $newContent = $content -replace "com\.lionico\.$OldName", "com.lionico.$NewName"
    $newContent = $newContent -replace "Lionico $OldAppName", "$AppName"
    $newContent = $newContent -replace "Lionico$OldTitleName", "$NewTitleName"
    $newContent = $newContent -replace "Theme\.Lionico\.$OldTitleName", "Theme.Lionico.$NewTitleName"

    # Specific for rootProject.name in settings.gradle.kts
    if ($file.Name -eq "settings.gradle.kts") {
        $newContent = $newContent -replace 'rootProject\.name = ".*"', "rootProject.name = `"$AppName`""
    }

    # Specific for strings.xml app_name
    if ($file.Name -eq "strings.xml") {
        $newContent = $newContent -replace '<string name="app_name">.*</string>', "<string name=`"app_name`">$AppName</string>"
    }

    if ($content -ne $newContent) {
        Set-Content $file.FullName $newContent
        Write-Host "Updated: $($file.FullName)" -ForegroundColor Green
    }
}

# 2. Rename directories
Write-Host "`n=== Renaming directory structure ===" -ForegroundColor Cyan
$srcDirs = Get-ChildItem -Path . -Recurse -Directory -Filter $OldName | Where-Object { $_.FullName -like "*\src\*\java\com\lionico\*" }

foreach ($dir in $srcDirs) {
    $parent = Split-Path $dir.FullName -Parent
    $destination = Join-Path $parent $NewName

    if (Test-Path $destination) {
        Write-Host "Warning: Destination $destination already exists, merging content..." -ForegroundColor Yellow
        Move-Item -Path "$($dir.FullName)\*" -Destination $destination -Force
        Remove-Item $dir.FullName -Recurse -Force
    } else {
        Move-Item -Path $dir.FullName -Destination $destination
        Write-Host "Moved: $($dir.FullName) -> $NewName" -ForegroundColor Green
    }
}

Write-Host "`n✅ Renaming completed! Please sync your project with Gradle." -ForegroundColor Green
