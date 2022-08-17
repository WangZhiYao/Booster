package com.yizhenwind.booster.order.ui.category.detail

import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.base.BaseViewHolder
import com.yizhenwind.booster.order.databinding.ItemSubjectBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class SubjectViewHolder(
    private val binding: ItemSubjectBinding
) : BaseViewHolder<Subject>(binding.root) {

    override fun bind(data: Subject) {
        binding.tvSubjectContent.text = data.content
    }

}