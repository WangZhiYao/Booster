package com.yizhenwind.booster.component.base

import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KProperty1

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/5
 */
open class IDDiffCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val diffProperty: KProperty1<T, Long>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])

    open fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        diffProperty.get(oldItem) == diffProperty.get(newItem)

    open fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}