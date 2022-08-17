package com.yizhenwind.booster.character.ui.detail

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
sealed class CharacterDetailSideEffect : ISideEffect {

    data class DeleteCharacterFailure(@StringRes val errorMessage: Int) : CharacterDetailSideEffect()

    object DeleteCharacterSuccess : CharacterDetailSideEffect()
}