package com.yizhenwind.booster.order.category.ui.tab.subject

import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.base.BaseViewHolder
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.order.databinding.ItemCategorySubjectBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class CategorySubjectViewHolder(
    private val binding: ItemCategorySubjectBinding
) : BaseViewHolder<Subject>(binding.root) {

    override fun bind(data: Subject) {
        binding.apply {
            data.apply {
                tvSubjectContent.text = content
                btnCreateOrder.setIntervalClickListener { }
            }
        }
    }

}