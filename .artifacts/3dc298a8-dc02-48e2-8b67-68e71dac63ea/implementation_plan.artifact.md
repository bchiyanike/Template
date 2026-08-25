# Stage 0: Project Baseline & De-cluttering

## Goal
Establish a clean baseline for the project by removing hardcoded resources, cleaning up dependencies, and preparing the architecture for multi-module migration.

## Proposed Changes

### [Cleanup]
#### [MODIFY] [MainActivity.kt](file:///C:/Users/Blessing/AndroidStudioProjects/Template/app/src/main/java/com/lionico/template/MainActivity.kt)
- Extract all hardcoded strings to `strings.xml`.
- Clean up any unused imports.

#### [MODIFY] [strings.xml](file:///C:/Users/Blessing/AndroidStudioProjects/Template/app/src/main/res/values/strings.xml)
- Add extracted strings from `MainActivity.kt`.

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/Blessing/AndroidStudioProjects/Template/app/build.gradle.kts)
- Remove commented-out dependencies (ADS, Serialization).
- Organize current dependencies into logical groups.

## Baseline Report
- **Package**: `com.lionico.template`
- **Architecture**: Single-module (`:app`), Hilt for DI, Jetpack Compose for UI.
- **Current Stack**: Kotlin 2.1.0, AGP 8.13.2 (will be updated in Stage 1).
- **Hardcoded Items**: Found in `MainActivity.kt`.

## Verification Plan
### Manual Verification
- Build and run the app to ensure no UI changes or regressions.
- Verify `strings.xml` contains all UI text.
