package com.yizhenwind.booster.home.ui.customer

import com.yizhenwind.booster.framework.base.BaseViewHolder
import com.yizhenwind.booster.framework.ext.setIntervalClickListener
import com.yizhenwind.booster.home.R
import com.yizhenwind.booster.home.databinding.ItemCustomerSummaryBinding
import com.yizhenwind.booster.model.CustomerSummary

/**
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
class CustomerSummaryViewHolder(
    private val binding: ItemCustomerSummaryBinding
) : BaseViewHolder<CustomerSummary>(binding.root) {

    var onMoreActionClickListener: ((CustomerSummary) -> Unit)? = null

    override fun bind(data: CustomerSummary) {
        binding.apply {
            data.apply {
                tvCustomerName.text = name
                tvCustomerSummary.text = root.context.getString(
                    R.string.item_customer_summary,
                    characterCount,
                    orderCount
                )
            }

            root.setIntervalClickListener { onItemClickListener?.invoke(data) }
            ibMore.setIntervalClickListener { onMoreActionClickListener?.invoke(data) }
        }
    }
}