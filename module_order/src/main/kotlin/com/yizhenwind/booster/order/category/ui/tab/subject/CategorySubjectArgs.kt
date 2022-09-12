package com.yizhenwind.booster.order.category.ui.tab.subject

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IFragmentArgDeserializer
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
data class CategorySubjectArgs(val categoryId: Long) : IFragmentArgs {

    override fun newInstance(): Fragment = CategorySubjectFragment().apply {
        arguments = Bundle().apply {
            putLong(IntentKey.CATEGORY_ID, categoryId)
        }
    }

    companion object : IFragmentArgDeserializer<CategorySubjectArgs> {

        override fun deserialize(arguments: Bundle): CategorySubjectArgs = arguments.run {
            CategorySubjectArgs(getLong(IntentKey.CATEGORY_ID))
        }

    }

}