package com.yizhenwind.booster.order.ui.category.main

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.IViewState

/**
 * 分类列表 ViewState
 *
 * @author WangZhiYao
 * @since 2022/7/20
 */
sealed class CategoryListViewState : IViewState {

    data class Init(val categoryList: PagingData<Category> = PagingData.empty()) :
        CategoryListViewState()

}