package com.yizhenwind.booster.framework.base

import android.view.View
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.framework.databinding.ActivityBaseTabMviBinding
import com.yizhenwind.booster.framework.ext.setupFragmentWithTab
import com.yizhenwind.booster.framework.ext.viewBindings

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/22
 */
abstract class BaseTabMVIActivity<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    BaseMVIActivity<STATE, SIDE_EFFECT>() {

    protected open val binding by viewBindings<ActivityBaseTabMviBinding>()

    override fun getRootView(): View = binding.root

    override fun initPage() {
        super.initPage()
        binding.apply {
            toolbar.apply {
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
            viewPager.setupFragmentWithTab(
                this@BaseTabMVIActivity,
                tabLayout,
                getTabTitleList(),
                getFragmentList()
            )
        }
    }

    fun setPageTitle(title: String) {
        binding.collapsingToolbarLayout.title = title
    }

    abstract fun getTabTitleList(): List<Int>

    abstract fun getFragmentList(): List<Fragment>

}