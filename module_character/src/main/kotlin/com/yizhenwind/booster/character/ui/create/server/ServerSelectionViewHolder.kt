package com.yizhenwind.booster.character.ui.create.server

import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.character.databinding.ItemServerSelectionBinding
import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.component.ext.setIntervalClickListener

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/5
 */
class ServerSelectionViewHolder(
    private val binding: ItemServerSelectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    var onServerSelectedListener: ((Server) -> Unit)? = null

    fun bind(server: Server, isChecked: Boolean) {
        binding.apply {
            tvServerName.text = server.name
            checkbox.isChecked = isChecked
            root.setIntervalClickListener {
                onServerSelectedListener?.invoke(server)
            }
        }
    }
}