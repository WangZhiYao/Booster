package com.yizhenwind.booster.mediator.order

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.mediator.IService
import kotlinx.coroutines.flow.Flow

/**
 * 订单模块对外接口
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
interface IOrderService : IService {

    /**
     * 启动创建订单
     *
     * @param context
     * @param customerId 用户ID
     * @param characterId 角色ID
     */
    fun launchCreateOrder(context: Context, customerId: Long = 0L, characterId: Long = 0L)

    /**
     * 启动分类列表页面
     */
    fun launchCategoryList(context: Context)

    /**
     * 启动创建分类页面
     */
    fun launchCreateCategory(context: Context)

    /**
     * 订阅订单列表
     */
    fun observeOrderList(): Flow<PagingData<Order>>

    /**
     * 根据客户ID订阅订单列表
     */
    fun observeOrderListByCustomerId(customerId: Long): Flow<PagingData<Order>>

    /**
     * 根据角色ID订阅订单列表
     */
    fun observeOrderListByCharacterId(characterId: Long): Flow<PagingData<Order>>


}