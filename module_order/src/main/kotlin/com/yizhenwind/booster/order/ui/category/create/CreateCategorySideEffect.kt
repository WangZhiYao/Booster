package com.yizhenwind.booster.order.ui.category.create

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 * @author WangZhiYao
 * @since 2022/7/21
 */
sealed class CreateCategorySideEffect : ISideEffect {

    data class ShowTitleError(@StringRes val errorMessage: Int) : CreateCategorySideEffect()

    object HideTitleError : CreateCategorySideEffect()

    data class CreateCategoryFailure(@StringRes val errorMessage: Int) : CreateCategorySideEffect()
}
