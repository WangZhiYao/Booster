package com.yizhenwind.booster.order.ui.category.detail

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.order.databinding.ItemSubjectBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class SubjectAdapter : PagingDataAdapter<Subject, SubjectViewHolder>(SubjectComparator) {

    object SubjectComparator : DiffUtil.ItemCallback<Subject>() {

        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder =
        SubjectViewHolder(parent.viewBinding(ItemSubjectBinding::inflate))

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}