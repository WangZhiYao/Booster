package com.yizhenwind.booster.home.ui.customer

import com.yizhenwind.booster.framework.base.BaseViewHolder
import com.yizhenwind.booster.framework.ext.setIntervalClickListener
import com.yizhenwind.booster.home.R
import com.yizhenwind.booster.home.databinding.ItemCustomerBinding
import com.yizhenwind.booster.model.Customer

/**
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
class CustomerViewHolder(
    private val binding: ItemCustomerBinding
) : BaseViewHolder<Customer>(binding.root) {

    var onCreateCharacterClickListener: (() -> Unit)? = null
    var onCreateOrderClickListener: (() -> Unit)? = null

    override fun bind(data: Customer) {
        binding.apply {
            data.apply {
                tvCustomerName.text = name
                root.context.apply {
                    tvCustomerContact.text = getString(
                        R.string.item_customer_character_contact_prefix,
                        contactType.value,
                        contact
                    )
                    tvCustomerRemark.text = getString(
                        R.string.item_customer_character_remark_prefix,
                        if (data.remark.isNullOrBlank()) getString(R.string.item_customer_character_remark_empty) else data.remark
                    )
                }
            }

            root.setIntervalClickListener { onItemClickListener?.invoke(data) }
            btnCustomerCreateCharacter.setIntervalClickListener { onCreateCharacterClickListener?.invoke() }
            btnCustomerCreateOrder.setIntervalClickListener { onCreateOrderClickListener?.invoke() }
        }
    }
}