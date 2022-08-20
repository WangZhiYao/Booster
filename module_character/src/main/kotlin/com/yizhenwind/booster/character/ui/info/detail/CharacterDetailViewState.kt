package com.yizhenwind.booster.character.ui.info.detail

import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
sealed class CharacterDetailViewState : IViewState {

    object Init : CharacterDetailViewState()

    data class GetCharacterSuccess(val character: Character) : CharacterDetailViewState()

}