package com.yizhenwind.booster.customer.ui.detail

import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
sealed class CustomerDetailViewState : IViewState {

    object Init : CustomerDetailViewState()
}