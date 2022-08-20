package com.yizhenwind.booster.component.base

import androidx.paging.PagingDataAdapter

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
abstract class BasePagingDataFragment<T : Any, VH : BaseViewHolder<T>> :
    BaseListFragment<VH>() {

    override val adapter by lazy { getListAdapter() }

    abstract override fun getListAdapter(): PagingDataAdapter<T, VH>

}