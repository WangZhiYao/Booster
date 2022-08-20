package com.yizhenwind.booster.customer.ui.info

import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
sealed class CustomerInfoViewState : IViewState {

    object Init : CustomerInfoViewState()
}