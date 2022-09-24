package com.yizhenwind.booster.home.ui.customer

import androidx.annotation.StringRes
import com.yizhenwind.booster.framework.base.ISideEffect


/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
sealed class CustomerSummarySideEffect : ISideEffect {

    object DeleteCustomerSuccess : CustomerSummarySideEffect()

    data class DeleteCustomerFailure(@StringRes val errorMessage: Int) : CustomerSummarySideEffect()
}