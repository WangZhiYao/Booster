package com.yizhenwind.booster.order.ui.category.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class CreateCategoryLaunchArgs : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateCategoryActivity::class.java)
}