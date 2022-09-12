package com.yizhenwind.booster.order.category.ui.tab.detail

import com.yizhenwind.booster.common.ext.formatToDateTime
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import com.yizhenwind.booster.order.databinding.FragmentCategoryDetailBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class CategoryDetailFragment :
    BaseFragment<FragmentCategoryDetailBinding>(FragmentCategoryDetailBinding::inflate) {

    private val args by fragmentArgs<CategoryDetailArgs>()

    override fun initPage() {
        binding.apply {
            args.category.apply {
                tlvCategoryTitle.content = title
                tlvCategoryDescription.content = description
                tlvCategoryCreateTime.content = createTime.formatToDateTime()
            }
        }
    }
}