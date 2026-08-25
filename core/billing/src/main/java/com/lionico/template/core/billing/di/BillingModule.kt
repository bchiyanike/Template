package com.lionico.template.core.billing.di

import com.lionico.template.core.billing.BillingManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BillingModule {
    @Provides
    @Singleton
    fun provideBillingManager(
        billingManager: BillingManager,
    ): BillingManager = billingManager
}
