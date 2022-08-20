package com.yizhenwind.booster.component.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
class BaseFragmentStateAdapter : FragmentStateAdapter {

    private val fragmentList: List<Fragment>

    constructor(activity: FragmentActivity, fragmentList: List<Fragment>) : super(activity) {
        this.fragmentList = fragmentList
    }

    constructor(fragment: Fragment, fragmentList: List<Fragment>) : super(fragment) {
        this.fragmentList = fragmentList
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}