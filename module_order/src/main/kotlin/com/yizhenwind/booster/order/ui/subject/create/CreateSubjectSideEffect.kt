package com.yizhenwind.booster.order.ui.subject.create

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
sealed class CreateSubjectSideEffect : ISideEffect {

    data class ShowCategoryError(@StringRes val errorMessage: Int) : CreateSubjectSideEffect()

    object HideCategoryError : CreateSubjectSideEffect()

    data class ShowContentError(@StringRes val errorMessage: Int) : CreateSubjectSideEffect()

    object HideContentError : CreateSubjectSideEffect()

    data class ShowSnake(@StringRes val errorMessage: Int) : CreateSubjectSideEffect()

}
