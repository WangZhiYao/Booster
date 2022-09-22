package com.yizhenwind.booster.customer.di

import com.yizhenwind.booster.customer.service.CustomerServiceImpl
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/21
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CustomerModule {

    @Binds
    abstract fun bindCustomerProvider(customerProviderImpl: CustomerServiceImpl): ICustomerService

}