package com.yizhenwind.booster.order.ui.subject.create

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.R
import com.yizhenwind.booster.component.databinding.ItemDropdownText1lineBinding
import com.yizhenwind.booster.component.ext.viewBinding

/**
 * 创建订单页 客户下拉框适配器
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
class CategoryAdapter(
    context: Context,
    private val customerList: List<Category>
) : ArrayAdapter<Category>(context, R.layout.item_dropdown_text_1line) {

    private val filteredCategoryList: ArrayList<Category> = ArrayList()

    override fun getItem(position: Int): Category = filteredCategoryList[position]

    override fun getCount(): Int = filteredCategoryList.size

    override fun getFilter(): Filter = CategoryFilter()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemDropdownText1lineBinding =
            convertView.ifNullOrElse({ parent.viewBinding(ItemDropdownText1lineBinding::inflate) },
                { it.tag as ItemDropdownText1lineBinding })
        binding.tvDropdownText.text = getItem(position).title
        return binding.root.apply { tag = binding }
    }

    private inner class CategoryFilter : Filter() {

        private val filteredList: ArrayList<Category> = ArrayList()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredList.clear()
            val results = FilterResults()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(customerList)
            } else {
                val filterPattern = constraint.toString().trim()
                customerList.forEach {
                    if (it.title.contains(filterPattern, true)) {
                        filteredList.add(it)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            filteredCategoryList.clear()
            filteredCategoryList.addAll(results.values as List<Category>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Category?)?.title ?: ""
        }
    }
}