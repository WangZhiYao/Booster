package com.yizhenwind.booster.order.data.domain

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
class ObserveOrderListUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) : IUseCase {

    operator fun invoke(): Flow<PagingData<Order>> =
        orderRepository.observeOrderList()

}