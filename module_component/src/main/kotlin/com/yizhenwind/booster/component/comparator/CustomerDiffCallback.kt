package com.yizhenwind.booster.component.comparator

import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.BaseDiffCallback

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
data class CustomerDiffCallback(
    private val oldList: List<Customer>,
    private val newList: List<Customer>
) : BaseDiffCallback<Customer>(oldList, newList) {

    override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean =
        oldItem == newItem

}