package com.yizhenwind.booster.component.base

import com.yizhenwind.booster.component.databinding.FragmentBasePagingMviBinding

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
abstract class BasePagingDataMVIFragment<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    BaseMVIFragment<FragmentBasePagingMviBinding, STATE, SIDE_EFFECT>(FragmentBasePagingMviBinding::inflate) {

    override fun initPage() {
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    override fun onDestroyView() {
        // Otherwise the adapter is going to hold a reference to the RecyclerView
        // which should have already gone out of memory.
        binding.rvList.adapter = null
        super.onDestroyView()
    }
}