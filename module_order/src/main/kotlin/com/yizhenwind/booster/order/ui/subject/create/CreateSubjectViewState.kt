package com.yizhenwind.booster.order.ui.subject.create

import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
sealed class CreateSubjectViewState : IViewState {

    data class Init(val categoryList: List<Category>) : CreateSubjectViewState()

    data class CreateSubjectSuccess(val subject: Subject) : CreateSubjectViewState()
}
