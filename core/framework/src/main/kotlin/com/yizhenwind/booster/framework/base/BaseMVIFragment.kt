package com.yizhenwind.booster.framework.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
abstract class BaseMVIFragment<out VB : ViewBinding, STATE : IViewState, SIDE_EFFECT : ISideEffect>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(inflate) {

    protected open fun render(state: STATE) {

    }

    protected open fun handleSideEffect(sideEffect: SIDE_EFFECT) {

    }
}