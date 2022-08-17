package com.yizhenwind.booster.order.ui.category.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.data.domain.DeleteCategoryUseCase
import com.yizhenwind.booster.order.data.domain.ObserveSubjectListByCategoryIdUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/27
 */
@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val observeSubjectListByCategoryIdUseCase: ObserveSubjectListByCategoryIdUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
) : ContainerHost<CategoryDetailViewState, CategoryDetailSideEffect>, BaseViewModel() {

    override val container =
        container<CategoryDetailViewState, CategoryDetailSideEffect>(CategoryDetailViewState.Init())

    fun observeSubjectListByCategoryId(categoryId: Long) {
        intent {
            observeSubjectListByCategoryIdUseCase(categoryId)
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CategoryDetailViewState.Init(it)
                    }
                }
        }
    }

    fun deleteCategory(category: Category) {
        intent {
            deleteCategoryUseCase(category)
                .catch {
                    Timber.e(it)
                    postSideEffect(CategoryDetailSideEffect.DeleteCategoryFailure(R.string.error_delete_category_failed))
                }
                .collect { success ->
                    if (success) {
                        postSideEffect(CategoryDetailSideEffect.DeleteCategorySuccess)
                    } else {
                        postSideEffect(CategoryDetailSideEffect.DeleteCategoryFailure(R.string.error_delete_category_failed))
                    }
                }
        }
    }
}