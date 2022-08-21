package com.yizhenwind.booster.order.ui.subject.main

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.order.databinding.FragmentSubjectListBinding
import timber.log.Timber

/**
 * 项目列表
 *
 * @author WangZhiYao
 * @since 2022/7/18
 */
/*
@AndroidEntryPoint
class SubjectListFragment :
    BaseFragment<FragmentSubjectListBinding>(FragmentSubjectListBinding::inflate) {

    private val viewModel by viewModels<SubjectListViewModel>()

    private val adapter: SubjectAdapter by lazy { SubjectAdapter() }

    override fun init() {
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            rvCategorySubject.adapter = adapter
        }
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private fun render(state: SubjectListViewState) {
        when (state) {
            is SubjectListViewState.Init -> {
                Timber.d(state.categorySubjectItemList.toString())
                adapter.submitData(state.categorySubjectItemList)
            }
        }
    }
}*/
