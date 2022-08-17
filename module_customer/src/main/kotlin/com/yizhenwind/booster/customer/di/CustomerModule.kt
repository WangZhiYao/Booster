package com.yizhenwind.booster.customer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.yizhenwind.booster.customer.service.CustomerServiceImpl
import com.yizhenwind.booster.mediator.customer.ICustomerService

/**
 * 用户模块对外接口注册
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CustomerModule {

    @Binds
    abstract fun bindCustomerService(customerServiceImpl: CustomerServiceImpl): ICustomerService

}