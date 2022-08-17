package com.yizhenwind.booster.order.ui.create

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.yizhenwind.booster.common.constant.BillingCycle
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
class BillingCycleAdapter(
    context: Context,
    private val billingCycleList: List<BillingCycle>
) : ArrayAdapter<BillingCycle>(context, R.layout.item_dropdown_text_1line) {

    private val filteredBillingCycleList: ArrayList<BillingCycle> = ArrayList()

    override fun getItem(position: Int): BillingCycle =
        filteredBillingCycleList[position]

    override fun getCount(): Int = filteredBillingCycleList.size

    override fun getFilter(): Filter = BillingCycleFilter()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemDropdownText1lineBinding =
            convertView.ifNullOrElse({ parent.viewBinding(ItemDropdownText1lineBinding::inflate) },
                { it.tag as ItemDropdownText1lineBinding })
        binding.tvDropdownText.text = getItem(position).value

        return binding.root.apply { tag = binding }
    }

    private inner class BillingCycleFilter : Filter() {

        private val filteredList: ArrayList<BillingCycle> = ArrayList()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredList.clear()
            val results = FilterResults()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(billingCycleList)
            } else {
                val filterPattern = constraint.toString().trim()
                billingCycleList.forEach {
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
            filteredBillingCycleList.clear()
            filteredBillingCycleList.addAll(results.values as List<BillingCycle>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as BillingCycle?)?.value ?: ""
        }
    }
}