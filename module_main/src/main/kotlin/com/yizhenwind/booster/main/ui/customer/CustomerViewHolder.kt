package com.yizhenwind.booster.main.ui.customer

import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.BaseViewHolder
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.main.R
import com.yizhenwind.booster.main.databinding.ItemCustomerBinding

/**
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
class CustomerViewHolder(
    private val binding: ItemCustomerBinding
) : BaseViewHolder<Customer>(binding.root) {

    var onItemClickListener: (() -> Unit)? = null
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

            cvCustomer.setIntervalClickListener { onItemClickListener?.invoke() }
            btnCustomerCreateCharacter.setIntervalClickListener { onCreateCharacterClickListener?.invoke() }
            btnCustomerCreateOrder.setIntervalClickListener { onCreateOrderClickListener?.invoke() }
        }
    }
}