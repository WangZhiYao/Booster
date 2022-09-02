package com.yizhenwind.booster.component.base

/**
 * Activity 基类
 *
 * @author WangZhiYao
 * @since 2022/8/22
 */
abstract class BaseMVIActivity<STATE : IViewState, SIDE_EFFECT : ISideEffect> : BaseActivity() {

    protected open fun render(state: STATE) {

    }

    protected open fun handleSideEffect(sideEffect: SIDE_EFFECT) {

    }
}