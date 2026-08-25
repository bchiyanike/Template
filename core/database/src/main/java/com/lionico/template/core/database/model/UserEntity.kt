package com.lionico.template.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lionico.template.core.model.UserData

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val name: String?,
    val photoUrl: String?,
    val isPremium: Boolean,
)

fun UserEntity.asExternalModel() = UserData(
    id = id,
    email = email,
    name = name,
    photoUrl = photoUrl,
    isPremium = isPremium,
)

fun UserData.asEntity() = UserEntity(
    id = id,
    email = email,
    name = name,
    photoUrl = photoUrl,
    isPremium = isPremium,
)
