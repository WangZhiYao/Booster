package com.yizhenwind.booster.order.data.domain

import androidx.paging.PagingData
import com.yizhenwind.booster.model.Order
import com.yizhenwind.booster.order.data.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 根据客户ID订阅订单列表
 *
 * @author WangZhiYao
 * @since 2022/6/7
 */
class ObserveOrderListByCustomerIdUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {

    operator fun invoke(customerId: Long): Flow<PagingData<Order>> =
        orderRepository.observeOrderListByCustomerId(customerId)
}