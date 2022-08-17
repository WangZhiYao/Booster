package com.yizhenwind.booster.order.ui.subject.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.component.base.BaseDiffCallback
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.order.databinding.ItemSubjectBinding
import com.yizhenwind.booster.order.databinding.ItemSubjectListCategoryBinding
import com.yizhenwind.booster.order.databinding.ItemSubjectListCategoryEmptyBinding
import com.yizhenwind.booster.order.ui.category.detail.SubjectViewHolder

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/5
 */
class SubjectAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class CategorySubjectItemComparator(
        oldList: List<CategorySubjectItem>,
        newList: List<CategorySubjectItem>
    ) : BaseDiffCallback<CategorySubjectItem>(oldList, newList) {
        override fun areItemsTheSame(
            oldItem: CategorySubjectItem,
            newItem: CategorySubjectItem
        ): Boolean = false

        override fun areContentsTheSame(
            oldItem: CategorySubjectItem,
            newItem: CategorySubjectItem
        ): Boolean = false
    }

    private var categorySubjectItemList: List<CategorySubjectItem> = emptyList()

    fun submitData(categorySubjectItemList: List<CategorySubjectItem>) {
        val result = DiffUtil.calculateDiff(
            CategorySubjectItemComparator(
                this.categorySubjectItemList,
                categorySubjectItemList
            )
        )
        this.categorySubjectItemList = categorySubjectItemList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_CATEGORY -> SubjectListCategoryViewHolder(
                parent.viewBinding(
                    ItemSubjectListCategoryBinding::inflate
                )
            )
            VIEW_TYPE_CATEGORY_EMPTY -> SubjectListCategoryEmptyViewHolder(
                parent.viewBinding(
                    ItemSubjectListCategoryEmptyBinding::inflate
                )
            )
            VIEW_TYPE_SUBJECT -> SubjectViewHolder(parent.viewBinding(ItemSubjectBinding::inflate))
            else -> throw IllegalArgumentException("unknown viewType: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_CATEGORY -> {
                val categoryItem = categorySubjectItemList[position]
                if (categoryItem is CategorySubjectItem.CategoryItem) {
                    (holder as SubjectListCategoryViewHolder).bind(categoryItem)
                }
            }
            VIEW_TYPE_SUBJECT -> {
                val subjectItem = categorySubjectItemList[position]
                if (subjectItem is CategorySubjectItem.SubjectItem) {
                    (holder as SubjectViewHolder).bind(subjectItem.subject)
                }
            }
        }
    }

    override fun getItemCount(): Int = categorySubjectItemList.size

    override fun getItemViewType(position: Int): Int =
        when (categorySubjectItemList[position]) {
            is CategorySubjectItem.CategoryItem -> VIEW_TYPE_CATEGORY
            is CategorySubjectItem.CategoryEmptyItem -> VIEW_TYPE_CATEGORY_EMPTY
            is CategorySubjectItem.SubjectItem -> VIEW_TYPE_SUBJECT
        }

    companion object {

        private const val VIEW_TYPE_CATEGORY = 1

        private const val VIEW_TYPE_CATEGORY_EMPTY = 2

        private const val VIEW_TYPE_SUBJECT = 3
    }
}