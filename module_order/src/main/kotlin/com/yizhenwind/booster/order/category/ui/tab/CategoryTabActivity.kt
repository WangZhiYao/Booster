package com.yizhenwind.booster.order.category.ui.tab

import androidx.fragment.app.Fragment
import com.yizhenwind.booster.component.base.BaseTabMVIActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.category.ui.tab.detail.CategoryDetailArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class CategoryTabActivity : BaseTabMVIActivity<CategoryTabViewState, CategoryTabSideEffect>() {

    private val args by activityArgs<CategoryTabArgs>()

    override fun getPageTitle(): String = args.category.title

    override fun getTabTitleList(): List<Int> =
        listOf(
            R.string.category_tab_detail,
            R.string.category_tab_subject
        )

    override fun getFragmentList(): List<Fragment> =
        listOf(
            CategoryDetailArgs(args.category).newInstance()
        )
}
