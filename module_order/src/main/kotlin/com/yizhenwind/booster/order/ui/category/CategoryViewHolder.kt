package com.yizhenwind.booster.order.ui.category

import androidx.core.view.isVisible
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.BaseViewHolder
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.order.databinding.ItemCategoryBinding

/**
 * 分类列表 ViewHolder
 *
 * @author WangZhiYao
 * @since 2022/7/20
 */
class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    BaseViewHolder<Category>(binding.root) {

    var onCategoryClickListener: (() -> Unit)? = null
    var onCreateSubjectClickListener: (() -> Unit)? = null

    override fun bind(data: Category) {
        binding.apply {
            cvCategory.setIntervalClickListener {
                onCategoryClickListener?.invoke()
            }
            data.apply {
                tvCategoryTitle.text = title
                tvCategoryDescription.apply {
                    isVisible = !description.isNullOrBlank()
                    text = description
                }
                btnCustomerCreateCharacter.setIntervalClickListener {
                    onCreateSubjectClickListener?.invoke()
                }
            }
        }
    }
}