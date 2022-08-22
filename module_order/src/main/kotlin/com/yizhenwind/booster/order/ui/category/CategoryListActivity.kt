package com.yizhenwind.booster.order.ui.category

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.BasePagingDataMVIActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
@AndroidEntryPoint
class CategoryListActivity :
    BasePagingDataMVIActivity<Category, CategoryViewHolder, CategoryListViewState, CategoryListSideEffect>() {

    override val viewModel by viewModels<CategoryListViewModel>()
    override val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
    override val adapter = CategoryAdapter()

    override fun render(state: CategoryListViewState) {
        when (state) {
            is CategoryListViewState.Init -> {
                lifecycleScope.launch { adapter.submitData(state.categoryList) }
            }
        }
    }

    override fun handleSideEffect(sideEffect: CategoryListSideEffect) {

    }
}