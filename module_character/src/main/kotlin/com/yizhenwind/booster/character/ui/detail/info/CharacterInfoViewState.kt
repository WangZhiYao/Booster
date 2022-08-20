package com.yizhenwind.booster.character.ui.detail.info

import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
sealed class CharacterInfoViewState : IViewState {

    object Init : CharacterInfoViewState()

    data class GetCharacterSuccess(val character: Character) : CharacterInfoViewState()

}