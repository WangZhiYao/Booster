package com.yizhenwind.booster.character.ui.create.customer

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.character.databinding.ItemCustomerSelectionBinding
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IDDiffCallback
import com.yizhenwind.booster.component.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
class CustomerSelectionAdapter : RecyclerView.Adapter<CustomerSelectionViewHolder>() {

    var onCustomerSelectedListener: ((Customer) -> Unit)? = null

    var customerList: List<Customer> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(IDDiffCallback(field, value, Customer::id))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    var checkedCustomer: Customer? = null
        set(value) {
            val position = field.ifNullOrElse({ -1 }, { customerList.indexOf(it) })
            val newPosition = customerList.indexOf(value)
            field = value
            if (position != -1) {
                notifyItemChanged(position)
            }
            if (newPosition != -1) {
                notifyItemChanged(newPosition)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerSelectionViewHolder =
        CustomerSelectionViewHolder(parent.viewBinding(ItemCustomerSelectionBinding::inflate)).apply {
            onCustomerSelectedListener = { customer ->
                this@CustomerSelectionAdapter.onCustomerSelectedListener?.invoke(customer)
            }
        }

    override fun onBindViewHolder(holder: CustomerSelectionViewHolder, position: Int) {
        customerList[position].let { customer ->
            holder.bind(
                customer,
                checkedCustomer == customer
            )
        }
    }

    override fun getItemCount(): Int = customerList.size
}