package com.yizhenwind.booster.order.ui.subject.main

import com.yizhenwind.booster.component.base.BaseViewHolder
import com.yizhenwind.booster.order.databinding.ItemSubjectListCategoryBinding

/**
 *
 * @author WangZhiYao
 * @since 2022/8/8
 */
class SubjectListCategoryViewHolder(private val binding: ItemSubjectListCategoryBinding) :
    BaseViewHolder<CategorySubjectItem.CategoryItem>(binding.root) {

    override fun bind(data: CategorySubjectItem.CategoryItem) {
        data.apply {
            binding.tvCategoryTitle.text = category.title
        }
    }

}