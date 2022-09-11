package com.yizhenwind.booster.character.ui.create.customer

import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
data class CustomerSelectionViewState(val customerList: List<Customer> = emptyList()) : IViewState
