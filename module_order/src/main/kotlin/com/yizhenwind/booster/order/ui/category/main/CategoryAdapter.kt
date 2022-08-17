package com.yizhenwind.booster.order.ui.category.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.order.databinding.ItemCategoryBinding

/**
 * 分类列表 Adapter
 *
 * @author WangZhiYao
 * @since 2022/7/20
 */
class CategoryAdapter : PagingDataAdapter<Category, CategoryViewHolder>(CategoryComparator) {

    object CategoryComparator : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem == newItem
    }

    var onCategoryClickListener: ((Category) -> Unit)? = null
    var onCreateSubjectClickListener: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(parent.viewBinding(ItemCategoryBinding::inflate))

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.apply {
                bind(it)
                onCategoryClickListener = {
                    this@CategoryAdapter.onCategoryClickListener?.invoke(it)
                }
                onCreateSubjectClickListener = {
                    this@CategoryAdapter.onCreateSubjectClickListener?.invoke(it)
                }
            }
        }
    }
}