package com.yizhenwind.booster.home.ui.customer

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.framework.ext.viewBinding
import com.yizhenwind.booster.home.databinding.ItemCustomerSummaryBinding
import com.yizhenwind.booster.model.CustomerSummary

/**
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
class CustomerSummaryAdapter :
    PagingDataAdapter<CustomerSummary, CustomerSummaryViewHolder>(CustomerSummaryComparator) {

    object CustomerSummaryComparator : DiffUtil.ItemCallback<CustomerSummary>() {

        override fun areItemsTheSame(oldItem: CustomerSummary, newItem: CustomerSummary): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CustomerSummary,
            newItem: CustomerSummary
        ): Boolean =
            oldItem == newItem

    }

    var onItemClickListener: ((CustomerSummary) -> Unit)? = null
    var onMoreActionClickListener: ((CustomerSummary) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomerSummaryViewHolder(parent.viewBinding(ItemCustomerSummaryBinding::inflate)).apply {
            onItemClickListener = { customer ->
                this@CustomerSummaryAdapter.onItemClickListener?.invoke(customer)
            }
            onMoreActionClickListener = { customer ->
                this@CustomerSummaryAdapter.onMoreActionClickListener?.invoke(customer)
            }
        }

    override fun onBindViewHolder(holder: CustomerSummaryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}