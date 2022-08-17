package com.yizhenwind.booster.order.ui.category.detail

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.IActivityLaunchArgDeserializer
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
data class CategoryDetailLaunchArgs(
    val category: Category
) : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CategoryDetailActivity::class.java)
            .apply {
                putExtra(IntentKey.CATEGORY, category)
            }

    companion object : IActivityLaunchArgDeserializer<CategoryDetailLaunchArgs> {

        override fun deserialize(intent: Intent): CategoryDetailLaunchArgs =
            CategoryDetailLaunchArgs(
                requireNotNull(intent.getParcelableExtra(IntentKey.CATEGORY)) {
                    IntentKey.CATEGORY
                }
            )
    }
}
