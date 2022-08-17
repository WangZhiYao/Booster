package com.yizhenwind.booster.order.ui.subject.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.order.data.domain.ObserveCategorySubjectListUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/7/18
 */
@HiltViewModel
class SubjectListViewModel @Inject constructor(
    private val observeCategorySubjectListUseCase: ObserveCategorySubjectListUseCase
) : ContainerHost<SubjectListViewState, SubjectListSideEffect>, ViewModel() {

    override val container =
        container<SubjectListViewState, SubjectListSideEffect>(SubjectListViewState.Init())

    init {
        intent {
            observeCategorySubjectListUseCase()
                .collect { categorySubjectList ->
                    val categorySubjectItemList = ArrayList<CategorySubjectItem>()

                    categorySubjectList.forEach {
                        categorySubjectItemList.add(CategorySubjectItem.CategoryItem(it.category))
                        if (it.subjectList.isEmpty()) {
                            categorySubjectItemList.add(CategorySubjectItem.CategoryEmptyItem)
                            return@forEach
                        }
                        it.subjectList.forEach { subject ->
                            categorySubjectItemList.add(CategorySubjectItem.SubjectItem(subject))
                        }
                    }
                    Timber.d(categorySubjectItemList.toString())

                    reduce {
                        SubjectListViewState.Init(categorySubjectItemList)
                    }
                }
        }
    }

}