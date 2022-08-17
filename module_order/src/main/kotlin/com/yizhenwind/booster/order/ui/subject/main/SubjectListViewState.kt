package com.yizhenwind.booster.order.ui.subject.main

import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/7/18
 */
sealed class SubjectListViewState : IViewState {

    data class Init(val categorySubjectItemList: List<CategorySubjectItem> = emptyList()) :
        SubjectListViewState()

}