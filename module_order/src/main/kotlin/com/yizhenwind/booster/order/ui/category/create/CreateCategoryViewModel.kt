package com.yizhenwind.booster.order.ui.category.create

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.data.domain.CreateCategoryUseCase
import com.yizhenwind.booster.order.data.domain.GetCategoryByTitleUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 * 创建分类 ViewModel
 *
 * @author WangZhiYao
 * @since 2022/7/21
 */
@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    private val getCategoryByTitleUseCase: GetCategoryByTitleUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase
) : BaseMVIViewModel<CreateCategoryViewState, CreateCategorySideEffect>() {

    override val container =
        container<CreateCategoryViewState, CreateCategorySideEffect>(CreateCategoryViewState.Init)

    fun onTitleChanged(title: String?) {
        intent {
            if (title.isNullOrEmpty()) {
                postSideEffect(CreateCategorySideEffect.HideTitleError)
                return@intent
            }

            getCategoryByTitleUseCase(title).ifNullOrElse({
                postSideEffect(CreateCategorySideEffect.HideTitleError)
            }, {
                postSideEffect(CreateCategorySideEffect.ShowTitleError(R.string.error_create_category_title_exist))
                return@intent
            })
        }
    }

    fun createCategory(title: String?, description: String?) {
        intent {
            if (title.isNullOrBlank()) {
                postSideEffect(CreateCategorySideEffect.ShowTitleError(R.string.error_create_category_input_title))
                return@intent
            }

            createCategoryUseCase(title, description)
                .catch {
                    Timber.e(it)
                    postSideEffect(CreateCategorySideEffect.CreateCategoryFailure(R.string.error_create_category_failed))
                }
                .collect { category ->
                    category.ifNullOrElse({
                        postSideEffect(CreateCategorySideEffect.CreateCategoryFailure(R.string.error_create_category_failed))
                    }, {
                        reduce {
                            CreateCategoryViewState.CreateCategorySuccess(it)
                        }
                    })
                }
        }
    }
}