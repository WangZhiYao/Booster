package com.yizhenwind.booster.component.base

import androidx.viewbinding.ViewBinding
import com.yizhenwind.booster.component.databinding.ActivityBaseToolbarMviBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/22
 */
abstract class BaseToolbarMVIActivity<VB : ViewBinding, STATE : IViewState, SIDE_EFFECT : ISideEffect, VM : BaseMVIViewModel<STATE, SIDE_EFFECT>, CONTENT_FRAGMENT : BaseMVIFragment<VB, STATE, SIDE_EFFECT, VM>>
    : BaseMVIActivity<ActivityBaseToolbarMviBinding, STATE, SIDE_EFFECT, VM>(ActivityBaseToolbarMviBinding::inflate) {

    override fun initPage() {
        binding.apply {
            toolbar.apply {
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
            supportFragmentManager.beginTransaction()
                .add(container.id, getContent())
                .commit()
        }
    }

    abstract fun getContent(): CONTENT_FRAGMENT
}