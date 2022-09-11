package com.yizhenwind.booster.component.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KProperty1

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/5
 */
open class IDItemDiffCallback<T : Any>(
    private val diffProperty: KProperty1<T, Long>
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        diffProperty.get(oldItem) == diffProperty.get(newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

}