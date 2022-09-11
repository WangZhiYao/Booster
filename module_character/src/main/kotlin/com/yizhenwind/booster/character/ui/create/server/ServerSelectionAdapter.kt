package com.yizhenwind.booster.character.ui.create.server

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.character.databinding.ItemServerSelectionBinding
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.component.base.IDDiffCallback
import com.yizhenwind.booster.component.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/5
 */
class ServerSelectionAdapter : RecyclerView.Adapter<ServerSelectionViewHolder>() {

    var onServerSelectedListener: ((Server) -> Unit)? = null

    var serverList: List<Server> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(IDDiffCallback(field, value, Server::id))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    var checkedServer: Server? = null
        set(value) {
            val position = field.ifNullOrElse({ -1 }, { serverList.indexOf(it) })
            val newPosition = serverList.indexOf(value)
            field = value
            if (position != -1) {
                notifyItemChanged(position)
            }
            if (newPosition != -1) {
                notifyItemChanged(newPosition)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerSelectionViewHolder =
        ServerSelectionViewHolder(parent.viewBinding(ItemServerSelectionBinding::inflate)).apply {
            onServerSelectedListener = { server ->
                this@ServerSelectionAdapter.onServerSelectedListener?.invoke(server)
            }
        }

    override fun onBindViewHolder(holder: ServerSelectionViewHolder, position: Int) {
        serverList[position].let { server ->
            holder.bind(server, checkedServer == server)
        }
    }

    override fun getItemCount(): Int = serverList.size
}