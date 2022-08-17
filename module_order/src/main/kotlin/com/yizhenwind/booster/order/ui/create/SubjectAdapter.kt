package com.yizhenwind.booster.order.ui.create

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.component.R
import com.yizhenwind.booster.component.databinding.ItemDropdownText1lineBinding

/**
 * 创建订单页 角色下拉框适配器
 *
 * @author WangZhiYao
 * @since 2022/6/22
 */
class SubjectAdapter(
    context: Context,
    private val subjectList: List<Subject>
) : ArrayAdapter<Subject>(context, R.layout.item_dropdown_text_1line) {

    private val filteredSubjectList: ArrayList<Subject> = ArrayList()

    override fun getItem(position: Int): Subject =
        filteredSubjectList[position]

    override fun getCount(): Int = filteredSubjectList.size

    override fun getFilter(): Filter = SubjectFilter()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemDropdownText1lineBinding =
            convertView.ifNullOrElse({ parent.viewBinding(ItemDropdownText1lineBinding::inflate) },
                { it.tag as ItemDropdownText1lineBinding })

        binding.tvDropdownText.text = getItem(position).content

        return binding.root.apply { tag = binding }
    }

    private inner class SubjectFilter : Filter() {

        private val filteredList: ArrayList<Subject> = ArrayList()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredList.clear()
            val results = FilterResults()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(subjectList)
            } else {
                val filterPattern = constraint.toString().trim()
                subjectList.forEach {
                    if (it.content.contains(filterPattern, true)) {
                        filteredList.add(it)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            filteredSubjectList.clear()
            filteredSubjectList.addAll(results.values as List<Subject>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Subject?)?.content ?: ""
        }
    }
}