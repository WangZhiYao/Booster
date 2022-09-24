package com.yizhenwind.booster.customer.ui.tab

import androidx.annotation.StringRes
import com.yizhenwind.booster.framework.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
sealed class CustomerTabSideEffect : ISideEffect {

    data class GetCustomerFailure(@StringRes val errorMessage: Int) : CustomerTabSideEffect()

    object DeleteCustomerSuccess : CustomerTabSideEffect()

    data class DeleteCustomerFailure(@StringRes val errorMessage: Int) : CustomerTabSideEffect()

}