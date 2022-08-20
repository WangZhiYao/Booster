package com.yizhenwind.booster.character.ui.info.detail

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
sealed class CharacterDetailSideEffect : ISideEffect {

    data class GetCharacterFailure(@StringRes val errorMessage: Int) : CharacterDetailSideEffect()

}