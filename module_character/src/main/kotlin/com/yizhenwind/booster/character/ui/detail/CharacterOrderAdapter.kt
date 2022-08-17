package com.yizhenwind.booster.character.ui.detail

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.booster.character.databinding.ItemCharacterOrderBinding
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.ext.viewBinding

/**
 *
 * @author WangZhiYao
 * @since 2022/6/7
 */
class CharacterOrderAdapter : PagingDataAdapter<Order, CharacterOrderViewHolder>(OrderComparator) {

    object OrderComparator : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Order, newItem: Order) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterOrderViewHolder =
        CharacterOrderViewHolder(parent.viewBinding(ItemCharacterOrderBinding::inflate))

    override fun onBindViewHolder(holder: CharacterOrderViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}
