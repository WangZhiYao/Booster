package com.yizhenwind.booster.order.ui.create

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.databinding.ItemDropdownCharacterBinding

/**
 * 创建订单页 角色下拉框适配器
 *
 * @author WangZhiYao
 * @since 2022/6/22
 */
class CharacterAdapter(
    context: Context,
    private val characterList: List<CustomerCharacterList.Character>
) : ArrayAdapter<CustomerCharacterList.Character>(context, R.layout.item_dropdown_character) {

    private val filteredCharacterList: ArrayList<CustomerCharacterList.Character> = ArrayList()

    override fun getItem(position: Int): CustomerCharacterList.Character =
        filteredCharacterList[position]

    override fun getCount(): Int = filteredCharacterList.size

    override fun getFilter(): Filter = CharacterFilter()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemDropdownCharacterBinding =
            convertView.ifNullOrElse({ parent.viewBinding(ItemDropdownCharacterBinding::inflate) },
                { it.tag as ItemDropdownCharacterBinding })

        getItem(position).apply {
            binding.apply {
                tvCharacterName.text = name
                tvCharacterZoneServer.text = "${zone.name}: ${server.name}"
            }
        }

        return binding.root.apply { tag = binding }
    }

    private inner class CharacterFilter : Filter() {

        private val filteredList: ArrayList<CustomerCharacterList.Character> = ArrayList()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredList.clear()
            val results = FilterResults()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(characterList)
            } else {
                val filterPattern = constraint.toString().trim()
                characterList.forEach {
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
            filteredCharacterList.clear()
            filteredCharacterList.addAll(results.values as List<CustomerCharacterList.Character>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as CustomerCharacterList.Character?)?.name ?: ""
        }
    }
}