package com.yizhenwind.booster.order.ui.category.main

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.order.databinding.FragmentCategoryListBinding
import com.yizhenwind.booster.order.ui.category.detail.CategoryDetailLaunchArgs
import com.yizhenwind.booster.order.ui.subject.create.CreateSubjectLaunchArgs

/**
 * 分类列表
 *
 * @author WangZhiYao
 * @since 2022/7/21
 */
@AndroidEntryPoint
class CategoryListFragment :
    BaseFragment<FragmentCategoryListBinding>(FragmentCategoryListBinding::inflate) {

    private val viewModel by viewModels<CategoryListViewModel>()

    private val adapter by lazy { CategoryAdapter() }

    override fun init() {
        initView()
        initData()
    }

    private fun initView() {
        adapter.onCategoryClickListener = { category ->
            CategoryDetailLaunchArgs(category).launch(requireContext())
        }
        adapter.onCreateSubjectClickListener = { category ->
            CreateSubjectLaunchArgs(category.id).launch(requireContext())
        }
        binding.apply {
            rvCategory.adapter = adapter
        }
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    private suspend fun render(state: CategoryListViewState) {
        when (state) {
            is CategoryListViewState.Init -> adapter.submitData(state.categoryList)
        }
    }

    private fun handleSideEffect(sideEffect: CategoryListSideEffect) {

    }
}