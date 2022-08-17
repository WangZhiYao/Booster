package com.yizhenwind.booster.character.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/30
 */
sealed class CreateCharacterSideEffect : ISideEffect {

    data class ShowCustomerError(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()

    object HideCustomerError : CreateCharacterSideEffect()

    data class ShowZoneError(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()

    object HideZoneError : CreateCharacterSideEffect()

    data class ShowServerError(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()

    object HideServerError : CreateCharacterSideEffect()

    data class ShowAccountError(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()

    object HideAccountError : CreateCharacterSideEffect()

    data class ShowPasswordError(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()

    object HidePasswordError : CreateCharacterSideEffect()

    data class ShowSecurityLockError(@StringRes val errorMessage: Int) :
        CreateCharacterSideEffect()

    object HideSecurityLockError : CreateCharacterSideEffect()

    data class ShowCharacterNameError(@StringRes val errorMessage: Int) :
        CreateCharacterSideEffect()

    object HideCharacterNameError : CreateCharacterSideEffect()

    data class ShowSectError(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()

    object HideSectError : CreateCharacterSideEffect()

    data class ShowInternalError(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()

    object HideInternalError : CreateCharacterSideEffect()

    data class CreateCharacterFailed(@StringRes val errorMessage: Int) : CreateCharacterSideEffect()
}