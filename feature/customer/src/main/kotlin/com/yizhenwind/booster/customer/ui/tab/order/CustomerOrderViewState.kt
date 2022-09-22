package com.yizhenwind.booster.customer.ui.tab.order

import androidx.paging.PagingData
import com.yizhenwind.booster.framework.base.IViewState
import com.yizhenwind.booster.model.Order

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