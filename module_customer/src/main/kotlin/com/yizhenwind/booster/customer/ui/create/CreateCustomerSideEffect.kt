package com.yizhenwind.booster.customer.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/4/29
 */
sealed class CreateCustomerSideEffect : ISideEffect {

    data class ShowNameError(@StringRes val errorMessage: Int) : CreateCustomerSideEffect()

    object HideNameError : CreateCustomerSideEffect()

    data class ShowContactTypeError(@StringRes val errorMessage: Int) : CreateCustomerSideEffect()

    object HideContactTypeError : CreateCustomerSideEffect()

    data class ShowContactError(@StringRes val errorMessage: Int) : CreateCustomerSideEffect()

    object HideContactError : CreateCustomerSideEffect()

    data class CreateCustomerFailure(@StringRes val errorMessage: Int) : CreateCustomerSideEffect()
}
