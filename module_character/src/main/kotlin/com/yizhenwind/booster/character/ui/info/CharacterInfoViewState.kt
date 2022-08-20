package com.yizhenwind.booster.character.ui.info

import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
sealed class CharacterInfoViewState : IViewState {

    object Init : CharacterInfoViewState()
}