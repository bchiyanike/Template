package com.lionico.template.core.database.di

import com.lionico.template.core.database.LionicoDatabase
import com.lionico.template.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesUserDao(
        database: LionicoDatabase,
    ): UserDao = database.userDao()
}
