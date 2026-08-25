package com.lionico.template.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lionico.template.core.database.dao.UserDao
import com.lionico.template.core.database.model.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class LionicoDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
