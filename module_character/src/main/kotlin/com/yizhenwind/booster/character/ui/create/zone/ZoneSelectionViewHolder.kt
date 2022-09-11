package com.yizhenwind.booster.character.ui.create.zone

import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.character.databinding.ItemZoneSelectionBinding
import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.component.ext.setIntervalClickListener

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/4
 */
class ZoneSelectionViewHolder(
    private val binding: ItemZoneSelectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    var onZoneSelectedListener: ((Zone) -> Unit)? = null

    fun bind(zone: Zone, isChecked: Boolean) {
        binding.apply {
            tvZoneName.text = zone.name
            checkbox.isChecked = isChecked
            root.setIntervalClickListener {
                onZoneSelectedListener?.invoke(zone)
            }
        }
    }
}