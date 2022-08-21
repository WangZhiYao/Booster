package com.yizhenwind.booster.main.ui.order

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.main.databinding.ItemOrderBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
class OrderAdapter : PagingDataAdapter<Order, OrderViewHolder>(OrderComparator) {

    object OrderComparator : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        OrderViewHolder(parent.viewBinding(ItemOrderBinding::inflate))

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        getItem(position)?.let {
            holder.apply {
                bind(it)
            }
        }
    }
}