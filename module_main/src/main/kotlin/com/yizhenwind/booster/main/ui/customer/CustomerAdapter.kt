package com.yizhenwind.booster.main.ui.customer

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.comparator.CustomerComparator
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.main.databinding.ItemCustomerBinding

/**
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
class CustomerAdapter :
    PagingDataAdapter<Customer, CustomerViewHolder>(CustomerComparator()) {

    var onItemClickListener: ((Customer) -> Unit)? = null
    var onCreateCharacterClickListener: ((Customer) -> Unit)? = null
    var onCreateOrderClickListener: ((Customer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomerViewHolder(parent.viewBinding(ItemCustomerBinding::inflate)).apply {
            onItemClickListener = {
                this@CustomerAdapter.onItemClickListener?.invoke(it)
            }
        }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        getItem(position)?.let {
            holder.apply {
                bind(it)
                onCreateCharacterClickListener = {
                    this@CustomerAdapter.onCreateCharacterClickListener?.invoke(it)
                }
                onCreateOrderClickListener = {
                    this@CustomerAdapter.onCreateOrderClickListener?.invoke(it)
                }
            }
        }
    }
}