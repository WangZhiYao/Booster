package com.yizhenwind.booster.component.base

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.component.databinding.FragmentBasePagingMviBinding

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
abstract class BasePagingDataMVIFragment<T : Any, ADAPTER : PagingDataAdapter<T, VH>, VH : BaseViewHolder<T>, STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    BaseMVIFragment<FragmentBasePagingMviBinding, STATE, SIDE_EFFECT>(FragmentBasePagingMviBinding::inflate) {

    open val layoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(requireContext()) }
    protected abstract val adapter: ADAPTER

    override fun initPage() {
        super.initPage()
        binding.rvList.apply {
            layoutManager = this@BasePagingDataMVIFragment.layoutManager
            adapter = this@BasePagingDataMVIFragment.adapter
        }
    }

    override fun onDestroyView() {
        // Otherwise the adapter is going to hold a reference to the RecyclerView
        // which should have already gone out of memory.
        binding.rvList.adapter = null
        super.onDestroyView()
    }
}