package com.yizhenwind.booster.home.ui.customer

import androidx.paging.PagingData
import com.yizhenwind.booster.framework.base.IViewState
import com.yizhenwind.booster.model.CustomerSummary


/**
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
sealed class CustomerSummaryViewState : IViewState {

    data class Init(val customerSummaryList: PagingData<CustomerSummary> = PagingData.empty()) :
        CustomerSummaryViewState()
}