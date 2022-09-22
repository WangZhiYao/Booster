package com.yizhenwind.booster.customer.ui.tab.character

import androidx.core.view.isVisible
import com.yizhenwind.booster.customer.databinding.ItemCustomerCharacterBinding
import com.yizhenwind.booster.framework.base.BaseViewHolder
import com.yizhenwind.booster.framework.ext.setIntervalClickListener
import com.yizhenwind.booster.framework.util.glide.GlideApp
import com.yizhenwind.booster.model.Character

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/23
 */
class CustomerCharacterViewHolder(
    private val binding: ItemCustomerCharacterBinding
) : BaseViewHolder<Character>(binding.root) {

    var onCreateOrderClickListener: (() -> Unit)? = null

    override fun bind(data: Character) {
        binding.apply {
            data.apply {

                GlideApp.with(ivCharacterInternal)
                    .load(data.internal.iconUrl)
                    .into(ivCharacterInternal)

                tvCharacterSect.text = sect.name
                tvCharacterName.text = name
                tvCharacterZone.text = zone.name
                tvCharacterServer.text = server.name
                tvCharacterRemark.run {
                    isVisible = !remark.isNullOrBlank()
                    text = remark
                }

                root.setIntervalClickListener { onItemClickListener?.invoke(this) }

                btnCharacterCreateOrder.setIntervalClickListener {
                    onCreateOrderClickListener?.invoke()
                }
            }
        }
    }
}