package com.yizhenwind.booster.home.ui.customer

import androidx.paging.PagingData
import com.yizhenwind.booster.framework.base.IViewState
import com.yizhenwind.booster.model.Customer


/**
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
sealed class CustomerViewState : IViewState {

    data class Init(val customerList: PagingData<Customer> = PagingData.empty()) :
        CustomerViewState()
}