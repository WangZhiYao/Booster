package com.yizhenwind.booster.component.comparator

import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.common.model.Customer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
class CustomerComparator : DiffUtil.ItemCallback<Customer>() {

    override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean =
        oldItem == newItem
}