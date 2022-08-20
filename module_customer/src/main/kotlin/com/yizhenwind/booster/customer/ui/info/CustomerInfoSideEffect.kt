package com.yizhenwind.booster.customer.ui.info

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
sealed class CustomerInfoSideEffect : ISideEffect {

    object DeleteCustomerSuccess : CustomerInfoSideEffect()

    data class DeleteCustomerFailure(@StringRes val errorMessage: Int) : CustomerInfoSideEffect()

}