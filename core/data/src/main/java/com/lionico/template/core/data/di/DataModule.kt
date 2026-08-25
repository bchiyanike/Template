package com.lionico.template.core.data.di

import com.lionico.template.core.data.repository.OfflineFirstUserRepository
import com.lionico.template.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsUserRepository(
        userRepository: OfflineFirstUserRepository,
    ): UserRepository
}
