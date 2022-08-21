package com.yizhenwind.booster.order.ui.category

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
class CategoryListArgs : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CategoryListActivity::class.java)

}