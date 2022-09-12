package com.yizhenwind.booster.order.category.ui.tab

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.IActivityArgs
import com.yizhenwind.booster.component.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
data class CategoryTabArgs(val category: Category) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CategoryTabActivity::class.java).apply {
            putExtra(IntentKey.CATEGORY, category)
        }

    companion object : IActivityArgsDeserializer<CategoryTabArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CategoryTabArgs = intent.run {
            CategoryTabArgs(requireNotNull(getParcelableExtra(IntentKey.CATEGORY)) { IntentKey.CATEGORY })
        }

    }
}