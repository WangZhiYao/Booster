package com.yizhenwind.booster.order.ui.category.create

import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/7/21
 */
sealed class CreateCategoryViewState : IViewState {

    object Init : CreateCategoryViewState()

    data class CreateCategorySuccess(val category: Category) : CreateCategoryViewState()

}
