package com.yizhenwind.booster.order.ui.create

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.databinding.ItemDropdownCustomerBinding

/**
 * 创建订单页 客户下拉框适配器
 *
 * @author WangZhiYao
 * @since 2022/6/22
 */
class CustomerAdapter(
    context: Context,
    private val customerList: List<Customer>
) : ArrayAdapter<Customer>(context, R.layout.item_dropdown_customer) {

    private val filteredCustomerList: ArrayList<Customer> = ArrayList()

    override fun getItem(position: Int): Customer = filteredCustomerList[position]

    override fun getCount(): Int = filteredCustomerList.size

    override fun getFilter(): Filter = CustomerFilter()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemDropdownCustomerBinding =
            convertView.ifNullOrElse({ parent.viewBinding(ItemDropdownCustomerBinding::inflate) },
                { it.tag as ItemDropdownCustomerBinding })

        getItem(position).apply {
            binding.apply {
                tvCustomerName.text = name
                tvCustomerContact.text = "${contactType.value}: $contact"
            }
        }

        return binding.root.apply { tag = binding }
    }

    private inner class CustomerFilter : Filter() {

        private val filteredList: ArrayList<Customer> = ArrayList()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredList.clear()
            val results = FilterResults()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(customerList)
            } else {
                val filterPattern = constraint.toString().trim()
                customerList.forEach {
                    if (it.name.contains(filterPattern, true)) {
                        filteredList.add(it)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            filteredCustomerList.clear()
            filteredCustomerList.addAll(results.values as List<Customer>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Customer?)?.name ?: ""
        }
    }
}