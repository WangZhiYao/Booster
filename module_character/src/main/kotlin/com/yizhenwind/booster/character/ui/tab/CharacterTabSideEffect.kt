package com.yizhenwind.booster.character.ui.tab

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
sealed class CharacterTabSideEffect : ISideEffect {

    data class DeleteCharacterFailure(@StringRes val errorMessage: Int) : CharacterTabSideEffect()

    object DeleteCharacterSuccess : CharacterTabSideEffect()
}