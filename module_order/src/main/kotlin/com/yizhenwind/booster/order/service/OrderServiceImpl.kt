package com.yizhenwind.booster.order.service

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.mediator.order.IOrderService
import com.yizhenwind.booster.order.data.domain.ObserveOrderListByCharacterIdUseCase
import com.yizhenwind.booster.order.data.domain.ObserveOrderListByCustomerIdUseCase
import com.yizhenwind.booster.order.data.domain.ObserveOrderListUseCase
import com.yizhenwind.booster.order.ui.category.CategoryListArgs
import com.yizhenwind.booster.order.ui.category.create.CreateCategoryLaunchArgs
import com.yizhenwind.booster.order.ui.create.CreateOrderLaunchArgs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 订单模块对外接口实现类
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
class OrderServiceImpl @Inject constructor(
    private val observeOrderListUseCase: ObserveOrderListUseCase,
    private val observeOrderListByCustomerIdUseCase: ObserveOrderListByCustomerIdUseCase,
    private val observeOrderListByCharacterIdUseCase: ObserveOrderListByCharacterIdUseCase
) : IOrderService {

    override fun launchCreateOrder(context: Context, customerId: Long, characterId: Long) {
        CreateOrderLaunchArgs(customerId, characterId).launch(context)
    }

    override fun launchCategoryList(context: Context) {
        CategoryListArgs().launch(context)
    }

    override fun launchCreateCategory(context: Context) {
        CreateCategoryLaunchArgs().launch(context)
    }

    override fun observeOrderList(): Flow<PagingData<Order>> =
        observeOrderListUseCase()

    override fun observeOrderListByCustomerId(customerId: Long): Flow<PagingData<Order>> =
        observeOrderListByCustomerIdUseCase(customerId)

    override fun observeOrderListByCharacterId(characterId: Long): Flow<PagingData<Order>> =
        observeOrderListByCharacterIdUseCase(characterId)


}