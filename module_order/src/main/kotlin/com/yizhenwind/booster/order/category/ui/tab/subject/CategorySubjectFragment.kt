package com.yizhenwind.booster.order.category.ui.tab.subject

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.component.base.BaseListMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
@AndroidEntryPoint
class CategorySubjectFragment :
    BaseListMVIFragment<CategorySubjectViewState, CategorySubjectSideEffect>() {

    private val viewModel by viewModels<CategorySubjectViewModel>()
    private val args by fragmentArgs<CategorySubjectArgs>()
    private val adapter = CategorySubjectAdapter()

    override fun initView() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CategorySubjectFragment.adapter
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.observeSubjectByCategoryId(args.categoryId)
    }

    override fun render(state: CategorySubjectViewState) {
        lifecycleScope.launch {
            adapter.submitData(state.subjectList)
        }
    }

}