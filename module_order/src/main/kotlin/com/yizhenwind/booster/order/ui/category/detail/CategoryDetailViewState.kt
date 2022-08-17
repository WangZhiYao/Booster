package com.yizhenwind.booster.order.ui.category.detail

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/27
 */
sealed class CategoryDetailViewState : IViewState {

    data class Init(val subjectList: PagingData<Subject> = PagingData.empty()) :
        CategoryDetailViewState()
}