package com.yizhenwind.booster.order.service

import android.content.Context
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.mediator.order.IOrderService
import com.yizhenwind.booster.order.data.domain.ObserveOrderListByCharacterIdUseCase
import com.yizhenwind.booster.order.ui.category.create.CreateCategoryLaunchArgs
import com.yizhenwind.booster.order.ui.create.CreateOrderActivity
import javax.inject.Inject

/**
 * 订单模块对外接口实现类
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
class OrderServiceImpl @Inject constructor(
    private val observeOrderListByCharacterIdUseCase: ObserveOrderListByCharacterIdUseCase
) : IOrderService {

    override fun launchCreateOrder(context: Context, customerId: Long, characterId: Long) {
        CreateOrderActivity.start(context, customerId, characterId)
    }

    override fun launchCreateCategory(context: Context) {
        CreateCategoryLaunchArgs().launch(context)
    }

    override fun observeOrderListByCharacterId(characterId: Long): Flow<PagingData<Order>> =
        observeOrderListByCharacterIdUseCase(characterId)

}