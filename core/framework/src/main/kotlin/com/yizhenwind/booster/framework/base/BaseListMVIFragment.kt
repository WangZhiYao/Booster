package com.yizhenwind.booster.framework.base

import com.yizhenwind.booster.framework.databinding.FragmentBaseListMviBinding


/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
abstract class BaseListMVIFragment<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    BaseMVIFragment<FragmentBaseListMviBinding, STATE, SIDE_EFFECT>(FragmentBaseListMviBinding::inflate) {

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