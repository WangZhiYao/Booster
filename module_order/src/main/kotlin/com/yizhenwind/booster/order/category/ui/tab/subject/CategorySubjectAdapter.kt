package com.yizhenwind.booster.order.category.ui.tab.subject

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.order.databinding.ItemCategorySubjectBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class CategorySubjectAdapter :
    PagingDataAdapter<Subject, CategorySubjectViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySubjectViewHolder =
        CategorySubjectViewHolder(parent.viewBinding(ItemCategorySubjectBinding::inflate))

    override fun onBindViewHolder(holder: CategorySubjectViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.bind(this)
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Subject>() {

            override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem == newItem
        }

    }

}