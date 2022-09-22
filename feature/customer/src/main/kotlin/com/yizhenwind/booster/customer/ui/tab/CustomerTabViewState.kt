package com.yizhenwind.booster.customer.ui.tab

import com.yizhenwind.booster.framework.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
sealed class CustomerTabViewState : IViewState {

    object Init : CustomerTabViewState()
}