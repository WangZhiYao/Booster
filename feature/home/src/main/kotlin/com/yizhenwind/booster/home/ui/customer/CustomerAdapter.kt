package com.yizhenwind.booster.home.ui.customer

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.framework.ext.viewBinding
import com.yizhenwind.booster.home.databinding.ItemCustomerBinding
import com.yizhenwind.booster.model.Customer


/**
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
class CustomerAdapter :
    PagingDataAdapter<Customer, CustomerViewHolder>(CustomerComparator) {

    object CustomerComparator : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean =
            oldItem == newItem

    }

    var onItemClickListener: ((Customer) -> Unit)? = null
    var onCreateCharacterClickListener: ((Customer) -> Unit)? = null
    var onCreateOrderClickListener: ((Customer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomerViewHolder(parent.viewBinding(ItemCustomerBinding::inflate)).apply {
            onItemClickListener = { customer ->
                this@CustomerAdapter.onItemClickListener?.invoke(customer)
            }
        }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        getItem(position)?.let { customer ->
            holder.apply {
                bind(customer)
                onCreateCharacterClickListener = {
                    this@CustomerAdapter.onCreateCharacterClickListener?.invoke(customer)
                }
                onCreateOrderClickListener = {
                    this@CustomerAdapter.onCreateOrderClickListener?.invoke(customer)
                }
            }
        }
    }
}