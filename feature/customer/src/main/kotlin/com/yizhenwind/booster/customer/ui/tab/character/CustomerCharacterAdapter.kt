package com.yizhenwind.booster.customer.ui.tab.character

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.customer.databinding.ItemCustomerCharacterBinding
import com.yizhenwind.booster.framework.ext.viewBinding
import com.yizhenwind.booster.model.Character

/**
 * 客户角色
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
class CustomerCharacterAdapter :
    PagingDataAdapter<Character, CustomerCharacterViewHolder>(CharacterComparator) {

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem

    }

    var onCharacterClickListener: ((Character) -> Unit)? = null
    var onCreateOrderClickListener: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomerCharacterViewHolder(parent.viewBinding(ItemCustomerCharacterBinding::inflate)).apply {
            onItemClickListener = {
                this@CustomerCharacterAdapter.onCharacterClickListener?.invoke(it)
            }
        }

    override fun onBindViewHolder(holder: CustomerCharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.apply {
                bind(it)
                onCreateOrderClickListener = {
                    this@CustomerCharacterAdapter.onCreateOrderClickListener?.invoke(it)
                }
            }
        }
    }
}