package com.yizhenwind.booster.customer.ui.tab

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
sealed class CustomerTabSideEffect : ISideEffect {

    object DeleteCustomerSuccess : CustomerTabSideEffect()

    data class DeleteCustomerFailure(@StringRes val errorMessage: Int) : CustomerTabSideEffect()

}