package com.yizhenwind.booster.main.ui.customer

import androidx.core.view.isVisible
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.BaseViewHolder
import com.yizhenwind.booster.component.ext.setIntervalClickListener
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
                tvCustomerContact.text = String.format("%s: %s", contactType.value, contact)

                tvCustomerRemark.apply {
                    isVisible = !remark.isNullOrBlank()
                    text = remark
                }
            }

            cvCustomer.setIntervalClickListener { onItemClickListener?.invoke() }
            btnCustomerCreateCharacter.setIntervalClickListener { onCreateCharacterClickListener?.invoke() }
            btnCustomerCreateOrder.setIntervalClickListener { onCreateOrderClickListener?.invoke() }
        }
    }
}