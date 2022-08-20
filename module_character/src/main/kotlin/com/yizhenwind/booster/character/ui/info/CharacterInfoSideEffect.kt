package com.yizhenwind.booster.character.ui.info

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
sealed class CharacterInfoSideEffect : ISideEffect {

    data class DeleteCharacterFailure(@StringRes val errorMessage: Int) : CharacterInfoSideEffect()

    object DeleteCharacterSuccess : CharacterInfoSideEffect()
}