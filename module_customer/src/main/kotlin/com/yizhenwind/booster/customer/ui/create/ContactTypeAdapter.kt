package com.yizhenwind.booster.customer.ui.create

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.component.R
import com.yizhenwind.booster.component.databinding.ItemDropdownText1lineBinding
import com.yizhenwind.booster.component.ext.viewBinding

/**
 * 创建订单页 付款周期下拉框适配器
 *
 * @author WangZhiYao
 * @since 2022/6/22
 */
class ContactTypeAdapter(
    context: Context,
    private val contactTypeList: List<ContactType>
) : ArrayAdapter<ContactType>(context, R.layout.item_dropdown_text_1line) {

    private val filteredContactTypeList: ArrayList<ContactType> = ArrayList()

    override fun getItem(position: Int): ContactType =
        filteredContactTypeList[position]

    override fun getCount(): Int = filteredContactTypeList.size

    override fun getFilter(): Filter = ContactTypeFilter()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemDropdownText1lineBinding =
            convertView.ifNullOrElse({ parent.viewBinding(ItemDropdownText1lineBinding::inflate) },
                { it.tag as ItemDropdownText1lineBinding })

        binding.tvDropdownText.text = getItem(position).value

        return binding.root.apply { tag = binding }
    }

    private inner class ContactTypeFilter : Filter() {

        private val filteredList: ArrayList<ContactType> = ArrayList()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredList.clear()
            val results = FilterResults()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(contactTypeList)
            } else {
                val filterPattern = constraint.toString().trim()
                contactTypeList.forEach {
                    if (it.value.contains(filterPattern, true)) {
                        filteredList.add(it)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            filteredContactTypeList.clear()
            filteredContactTypeList.addAll(results.values as List<ContactType>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as ContactType?)?.value ?: ""
        }
    }
}