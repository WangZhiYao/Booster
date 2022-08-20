package com.yizhenwind.booster.component.base

import androidx.paging.PagingDataAdapter

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
abstract class BasePagingDataFragment<T : Any, ADAPTER : PagingDataAdapter<T, VH>, VH : BaseViewHolder<T>> :
    BaseListFragment<ADAPTER, VH>() {

    override val adapter by lazy { getListAdapter() }

    abstract override fun getListAdapter(): ADAPTER

}