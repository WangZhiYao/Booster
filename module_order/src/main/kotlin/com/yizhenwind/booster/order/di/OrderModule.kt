package com.yizhenwind.booster.order.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.yizhenwind.booster.mediator.order.IOrderService
import com.yizhenwind.booster.order.service.OrderServiceImpl

/**
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class OrderModule {

    @Binds
    abstract fun bindOrderService(orderServiceImpl: OrderServiceImpl): IOrderService

}