package com.yizhenwind.booster.component.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yizhenwind.booster.component.base.BaseFragmentStateAdapter

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
fun ViewPager2.setupWithFragmentList(activity: FragmentActivity, fragmentList: List<Fragment>) {
    adapter = BaseFragmentStateAdapter(activity, fragmentList)
    (getChildAt(0) as RecyclerView).apply {
        overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        isNestedScrollingEnabled = false
    }
}

fun ViewPager2.setupWithFragmentList(fragment: Fragment, fragmentList: List<Fragment>) {
    adapter = BaseFragmentStateAdapter(fragment, fragmentList)
    (getChildAt(0) as RecyclerView).apply {
        overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        isNestedScrollingEnabled = false
    }
}

fun ViewPager2.setupWithTab(
    activity: FragmentActivity,
    tabLayout: TabLayout,
    titleResIdList: List<Int>,
    fragmentList: List<Fragment>
) {
    setupWithFragmentList(activity, fragmentList)
    TabLayoutMediator(
        tabLayout,
        this
    ) { tab, position ->
        tab.setText(titleResIdList[position])
    }.attach()
}

fun ViewPager2.setupWithTab(
    fragment: Fragment,
    tabLayout: TabLayout,
    titleResIdList: List<Int>,
    fragmentList: List<Fragment>
) {
    setupWithFragmentList(fragment, fragmentList)
    TabLayoutMediator(
        tabLayout,
        this
    ) { tab, position ->
        tab.setText(titleResIdList[position])
    }.attach()
}