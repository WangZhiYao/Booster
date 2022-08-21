package com.yizhenwind.booster.component.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
abstract class BaseMVIFragment<out VB : ViewBinding, STATE : IViewState, SIDE_EFFECT : ISideEffect>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(inflate) {

    protected abstract val viewModel: BaseMVIViewModel<STATE, SIDE_EFFECT>

    override fun initPage() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    abstract fun render(state: STATE)

    abstract fun handleSideEffect(sideEffect: SIDE_EFFECT)

}