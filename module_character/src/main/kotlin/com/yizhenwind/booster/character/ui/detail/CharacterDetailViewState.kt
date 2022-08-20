package com.yizhenwind.booster.character.ui.detail

import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
sealed class CharacterDetailViewState : IViewState {

    object Init : CharacterDetailViewState()
}