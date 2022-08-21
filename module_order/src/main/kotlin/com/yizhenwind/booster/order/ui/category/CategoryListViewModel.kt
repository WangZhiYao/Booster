package com.yizhenwind.booster.order.ui.category

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.order.data.domain.ObserveCategoryListUseCase
import com.yizhenwind.booster.order.ui.category.CategoryListSideEffect
import com.yizhenwind.booster.order.ui.category.CategoryListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * 分类列表 ViewModel
 *
 * @author WangZhiYao
 * @since 2022/7/20
 */
@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val observeCategoryListUseCase: ObserveCategoryListUseCase
) : BaseMVIViewModel<CategoryListViewState, CategoryListSideEffect>() {

    override val container =
        container<CategoryListViewState, CategoryListSideEffect>(CategoryListViewState.Init())

    init {
        intent {
            observeCategoryListUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CategoryListViewState.Init(it)
                    }
                }
        }
    }
}