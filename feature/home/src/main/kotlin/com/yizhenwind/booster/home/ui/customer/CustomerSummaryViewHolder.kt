package com.yizhenwind.booster.home.ui.customer

import com.yizhenwind.booster.common.constant.ContactType
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

    var onCreateCharacterClickListener: ((Long) -> Unit)? = null
    var onCreateOrderClickListener: ((Long) -> Unit)? = null
    var onContactClickListener: ((ContactType, String) -> Unit)? = null
    var onDeleteCustomerClickListener: ((Long, String) -> Unit)? = null

    override fun bind(data: CustomerSummary) {
        binding.apply {
            data.apply {
                tvCustomerName.text = name
                tvCustomerSummary.text = root.context.getString(
                    R.string.item_customer_summary,
                    characterCount,
                    orderCount
                )

                btnCreateCharacter.setIntervalClickListener {
                    onCreateCharacterClickListener?.invoke(id)
                }

                btnCreateOrder.setIntervalClickListener {
                    onCreateOrderClickListener?.invoke(id)
                }

                ibContactType.apply {
                    setImageResource(
                        when (contactType) {
                            ContactType.QQ -> R.drawable.ic_contact_type_qq
                            ContactType.WECHAT -> R.drawable.ic_contact_type_wechat
                            ContactType.PHONE -> R.drawable.ic_contact_type_phone
                        }
                    )
                    setIntervalClickListener {
                        onContactClickListener?.invoke(contactType, contact)
                    }
                }

                ibCustomerDelete.setIntervalClickListener {
                    onDeleteCustomerClickListener?.invoke(id, name)
                }
            }

            root.setIntervalClickListener { onItemClickListener?.invoke(data) }
        }
    }
}