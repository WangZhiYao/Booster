package com.yizhenwind.booster.main.ui.customer

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
sealed class CustomerListViewState : IViewState {

    data class Init(val customerList: PagingData<Customer> = PagingData.empty()) :
        CustomerListViewState()
}