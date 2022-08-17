package com.yizhenwind.booster.customer.ui.detail

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
sealed class CustomerDetailSideEffect : ISideEffect {

    object DeleteCustomerSuccess : CustomerDetailSideEffect()

    data class DeleteCustomerFailure(@StringRes val errorMessage: Int) : CustomerDetailSideEffect()

}