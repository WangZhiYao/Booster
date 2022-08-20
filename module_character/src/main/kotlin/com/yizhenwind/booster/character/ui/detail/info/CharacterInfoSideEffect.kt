package com.yizhenwind.booster.character.ui.detail.info

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
sealed class CharacterInfoSideEffect : ISideEffect {

    data class GetCharacterFailure(@StringRes val errorMessage: Int) : CharacterInfoSideEffect()

}