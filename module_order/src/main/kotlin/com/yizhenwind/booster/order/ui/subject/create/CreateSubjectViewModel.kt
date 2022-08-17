package com.yizhenwind.booster.order.ui.subject.create

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.data.domain.CreateSubjectUseCase
import com.yizhenwind.booster.order.data.domain.GetCategoryListUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/7/19
 */
@HiltViewModel
class CreateSubjectViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val createSubjectUseCase: CreateSubjectUseCase
) : ContainerHost<CreateSubjectViewState, CreateSubjectSideEffect>, BaseViewModel() {

    override val container =
        container<CreateSubjectViewState, CreateSubjectSideEffect>(CreateSubjectViewState.Init(emptyList()))

    var categoryId: Long? = Constant.DEFAULT_ID

    init {
        init()
    }

    fun init() {
        intent {
            val categoryList = getCategoryListUseCase()
            reduce {
                CreateSubjectViewState.Init(categoryList)
            }
        }
    }

    fun onCategorySelect(categoryId: Long?) {
        this.categoryId = categoryId
        intent {
            if (categoryId == null || categoryId == Constant.DEFAULT_ID) {
                postSideEffect(CreateSubjectSideEffect.ShowCategoryError(R.string.error_create_subject_select_category))
            } else {
                postSideEffect(CreateSubjectSideEffect.HideCategoryError)
            }
        }
    }

    fun onContentInput(content: String?) {
        intent {
            if (content.isNullOrBlank()) {
                postSideEffect(CreateSubjectSideEffect.ShowContentError(R.string.error_create_subject_input_content))
            } else {
                postSideEffect(CreateSubjectSideEffect.HideContentError)
            }
        }
    }

    fun createSubject(categoryId: Long?, content: String?) {
        intent {
            if (categoryId == null || categoryId == Constant.DEFAULT_ID) {
                postSideEffect(CreateSubjectSideEffect.ShowCategoryError(R.string.error_create_subject_select_category))
                return@intent
            }

            if (content.isNullOrBlank()) {
                postSideEffect(CreateSubjectSideEffect.ShowContentError(R.string.error_create_subject_input_content))
                return@intent
            }

            createSubjectUseCase(categoryId, content)
                .catch {
                    Timber.e(it)
                    postSideEffect(CreateSubjectSideEffect.ShowSnake(R.string.error_create_subject_failed))
                }
                .collect { subject ->
                    subject.ifNullOrElse({
                        postSideEffect(CreateSubjectSideEffect.ShowSnake(R.string.error_create_subject_failed))
                    }, {
                        reduce {
                            CreateSubjectViewState.CreateSubjectSuccess(it)
                        }
                    })
                }
        }
    }
}