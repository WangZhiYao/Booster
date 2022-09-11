package com.yizhenwind.booster.character.ui.create

import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/4
 */
sealed class CreateCharacterViewState2 : IViewState {

    object Init : CreateCharacterViewState2()
}