package com.yizhenwind.booster.main.ui.order

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
sealed class OrderListViewState : IViewState {

    data class Init(val orderList: PagingData<Order> = PagingData.empty()) : OrderListViewState()
}