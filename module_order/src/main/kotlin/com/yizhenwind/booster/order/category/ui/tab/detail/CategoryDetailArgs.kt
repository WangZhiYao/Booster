package com.yizhenwind.booster.order.category.ui.tab.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.IFragmentArgDeserializer
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
data class CategoryDetailArgs(val category: Category) : IFragmentArgs {

    override fun newInstance(): Fragment = CategoryDetailFragment().apply {
        arguments = Bundle().apply {
            putParcelable(IntentKey.CATEGORY, category)
        }
    }

    companion object : IFragmentArgDeserializer<CategoryDetailArgs> {

        @JvmStatic
        override fun deserialize(arguments: Bundle): CategoryDetailArgs = arguments.run {
            CategoryDetailArgs(requireNotNull(getParcelable(IntentKey.CATEGORY)) { IntentKey.CATEGORY })
        }

    }
}