package com.yizhenwind.booster.order.category.ui.tab.subject

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.order.subject.data.domain.ObserveSubjectListByCategoryIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
@HiltViewModel
class CategorySubjectViewModel @Inject constructor(
    private val observeSubjectListByCategoryIdUseCase: ObserveSubjectListByCategoryIdUseCase
) : BaseMVIViewModel<CategorySubjectViewState, CategorySubjectSideEffect>() {

    override val container =
        container<CategorySubjectViewState, CategorySubjectSideEffect>(CategorySubjectViewState())

    fun observeSubjectByCategoryId(categoryId: Long) {
        intent {
            observeSubjectListByCategoryIdUseCase(categoryId)
                .cachedIn(viewModelScope)
                .collect { subjectList ->
                    reduce {
                        state.copy(subjectList = subjectList)
                    }
                }
        }
    }
}