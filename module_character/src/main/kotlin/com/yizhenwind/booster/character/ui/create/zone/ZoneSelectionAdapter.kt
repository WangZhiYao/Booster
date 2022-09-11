package com.yizhenwind.booster.character.ui.create.zone

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.character.databinding.ItemZoneSelectionBinding
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.component.base.IDDiffCallback
import com.yizhenwind.booster.component.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/4
 */
class ZoneSelectionAdapter : RecyclerView.Adapter<ZoneSelectionViewHolder>() {

    var onZoneSelectedListener: ((Zone) -> Unit)? = null

    var zoneList: List<Zone> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(IDDiffCallback(field, value, Zone::id))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    var checkedZone: Zone? = null
        set(value) {
            val position = field.ifNullOrElse({ -1 }, { zoneList.indexOf(it) })
            val newPosition = zoneList.indexOf(value)
            field = value
            if (position != -1) {
                notifyItemChanged(position)
            }
            if (newPosition != -1) {
                notifyItemChanged(newPosition)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneSelectionViewHolder =
        ZoneSelectionViewHolder(parent.viewBinding(ItemZoneSelectionBinding::inflate)).apply {
            onZoneSelectedListener = { zone ->
                this@ZoneSelectionAdapter.onZoneSelectedListener?.invoke(zone)
            }
        }

    override fun onBindViewHolder(holder: ZoneSelectionViewHolder, position: Int) {
        zoneList[position].let { zone -> holder.bind(zone, checkedZone == zone) }
    }

    override fun getItemCount(): Int = zoneList.size
}