package com.yizhenwind.booster.framework.widget.action

import com.yizhenwind.booster.framework.base.BaseViewHolder
import com.yizhenwind.booster.framework.databinding.ItemBottomOptionBinding
import com.yizhenwind.booster.framework.ext.setIntervalClickListener

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/23
 */
class ActionViewHolder<T : Action>(
    private val binding: ItemBottomOptionBinding
) : BaseViewHolder<T>(binding.root) {

    override fun bind(data: T) {
        binding.apply {
            ivOptionIcon.setImageResource(data.icon)
            tvOptionContent.setText(data.content)

            root.setIntervalClickListener {
                onItemClickListener?.invoke(data)
            }
        }
    }

}