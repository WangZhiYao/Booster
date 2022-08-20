package com.yizhenwind.booster.customer.ui.info.order

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.customer.databinding.ItemCustomerOrderBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/20
 */
class CustomerOrderAdapter : PagingDataAdapter<Order, CustomerOrderViewHolder>(OrderComparator) {

    object OrderComparator : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Order, newItem: Order) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerOrderViewHolder =
        CustomerOrderViewHolder(parent.viewBinding(ItemCustomerOrderBinding::inflate))

    override fun onBindViewHolder(holder: CustomerOrderViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
