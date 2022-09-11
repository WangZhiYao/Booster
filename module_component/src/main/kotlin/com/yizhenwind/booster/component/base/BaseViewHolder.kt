package com.yizhenwind.booster.component.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/20
 */
abstract class BaseViewHolder<T>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    var onItemClickListener: ((T) -> Unit)? = null

    abstract fun bind(data: T)

}