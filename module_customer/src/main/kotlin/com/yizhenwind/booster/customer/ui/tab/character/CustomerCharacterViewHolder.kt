package com.yizhenwind.booster.customer.ui.tab.character

import androidx.core.view.isVisible
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.BaseViewHolder
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.customer.databinding.ItemCustomerCharacterBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/23
 */
class CustomerCharacterViewHolder(
    private val binding: ItemCustomerCharacterBinding
) : BaseViewHolder<Character>(binding.root) {

    var onCharacterClickListener: (() -> Unit)? = null
    var onCreateOrderClickListener: (() -> Unit)? = null

    override fun bind(data: Character) {
        binding.apply {
            data.apply {
                tvCharacterSect.text = sect.name
                tvCharacterName.text = name
                tvCharacterZone.text = zone.name
                tvCharacterServer.text = server.name
                tvCharacterRemark.run {
                    isVisible = !remark.isNullOrBlank()
                    text = remark
                }

                cvCharacter.setIntervalClickListener { onCharacterClickListener?.invoke() }
                btnCharacterCreateOrder.setIntervalClickListener { onCreateOrderClickListener?.invoke() }
            }
        }
    }
}