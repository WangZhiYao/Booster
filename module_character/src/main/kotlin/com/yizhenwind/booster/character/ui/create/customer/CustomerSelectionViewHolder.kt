package com.yizhenwind.booster.character.ui.create.customer

import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.databinding.ItemCustomerSelectionBinding
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.ext.setIntervalClickListener

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
class CustomerSelectionViewHolder(private val binding: ItemCustomerSelectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var onCustomerSelectedListener: ((Customer) -> Unit)? = null

    fun bind(customer: Customer, isChecked: Boolean) {
        binding.apply {
            customer.apply {
                tvCustomerName.text = name
                tvCustomerContact.apply {
                    text = context.getString(
                        R.string.create_character_select_customer_contact,
                        contactType.value,
                        contact
                    )
                }
            }
            checkbox.isChecked = isChecked
            root.setIntervalClickListener {
                onCustomerSelectedListener?.invoke(customer)
            }
        }
    }
}