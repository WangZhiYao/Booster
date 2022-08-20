package com.yizhenwind.booster.component.base

import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.component.databinding.FragmentBaseListBinding

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
abstract class BaseListFragment<ADAPTER : RecyclerView.Adapter<VH>, VH : RecyclerView.ViewHolder> :
    BaseFragment<FragmentBaseListBinding>(FragmentBaseListBinding::inflate) {

    protected open val adapter by lazy { getListAdapter() }

    override fun init() {
        binding.apply {
            rvList.apply {
                layoutManager = this@BaseListFragment.getLayoutManager()
                adapter = this@BaseListFragment.adapter
            }
        }
    }

    abstract fun getLayoutManager(): RecyclerView.LayoutManager

    abstract fun getListAdapter(): ADAPTER

    override fun onDestroyView() {
        // Otherwise the adapter is going to hold a reference to the RecyclerView
        // which should have already gone out of memory.
        binding.rvList.adapter = null
        super.onDestroyView()
    }
}