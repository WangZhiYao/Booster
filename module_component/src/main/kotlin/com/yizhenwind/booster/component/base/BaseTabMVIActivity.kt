package com.yizhenwind.booster.component.base

import android.view.View
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.component.databinding.ActivityBaseTabMviBinding
import com.yizhenwind.booster.component.ext.setupFragmentWithTab
import com.yizhenwind.booster.component.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/22
 */
abstract class BaseTabMVIActivity<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    BaseMVIActivity<STATE, SIDE_EFFECT>() {

    protected open val binding by viewBinding(ActivityBaseTabMviBinding::inflate)

    override fun getRootView(): View = binding.root

    override fun initPage() {
        super.initPage()
        binding.apply {
            toolbar.apply {
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
            collapsingToolbarLayout.title = getPageTitle()
            viewPager.setupFragmentWithTab(
                this@BaseTabMVIActivity,
                tabLayout,
                getTabTitleList(),
                getFragmentList()
            )
        }
    }

    abstract fun getPageTitle(): String

    abstract fun getTabTitleList(): List<Int>

    abstract fun getFragmentList(): List<Fragment>
}