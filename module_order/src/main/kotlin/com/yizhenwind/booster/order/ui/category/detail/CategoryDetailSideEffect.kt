package com.yizhenwind.booster.order.ui.category.detail

import androidx.annotation.StringRes
import com.yizhenwind.booster.component.base.ISideEffect

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/27
 */
sealed class CategoryDetailSideEffect : ISideEffect {

    data class DeleteCategoryFailure(@StringRes val errorMessage: Int) : CategoryDetailSideEffect()

    object DeleteCategorySuccess : CategoryDetailSideEffect()
}