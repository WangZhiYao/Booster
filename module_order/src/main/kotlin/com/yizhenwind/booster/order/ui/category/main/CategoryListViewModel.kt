package com.yizhenwind.booster.order.ui.category.main

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.order.data.domain.ObserveCategoryListUseCase
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
) : ContainerHost<CategoryListViewState, CategoryListSideEffect>, BaseViewModel() {

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