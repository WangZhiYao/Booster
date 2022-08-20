package com.yizhenwind.booster.customer.ui.info.order

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/20
 */
sealed class CustomerOrderViewState : IViewState {

    data class Init(val orderList: PagingData<Order> = PagingData.empty()) :
        CustomerOrderViewState()

}