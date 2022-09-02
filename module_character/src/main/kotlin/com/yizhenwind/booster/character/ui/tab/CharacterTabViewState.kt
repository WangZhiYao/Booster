package com.yizhenwind.booster.character.ui.tab

import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
sealed class CharacterTabViewState : IViewState {

    object Init : CharacterTabViewState()
}