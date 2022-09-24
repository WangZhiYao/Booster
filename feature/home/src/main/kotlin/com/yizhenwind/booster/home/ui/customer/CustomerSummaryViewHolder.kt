package com.yizhenwind.booster.home.ui.customer

import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.util.ContactHelper
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
                root.context.apply {
                    tvCustomerSummary.text = getString(
                        R.string.item_customer_summary,
                        characterCount,
                        orderCount
                    )

                    ibContactType.setIntervalClickListener {
                        when (contactType) {
                            ContactType.QQ -> ContactHelper.attemptStartQQ(this, contact)
                            ContactType.WECHAT -> ContactHelper.attemptStartWeChat(this)
                            ContactType.PHONE -> ContactHelper.attemptStartDial(this, contact)
                        }
                    }
                }

                ibContactType.setImageResource(
                    when (contactType) {
                        ContactType.QQ -> R.drawable.ic_contact_type_qq
                        ContactType.WECHAT -> R.drawable.ic_contact_type_wechat
                        ContactType.PHONE -> R.drawable.ic_contact_type_phone
                    }
                )


            }

            root.setIntervalClickListener { onItemClickListener?.invoke(data) }
        }
    }
}