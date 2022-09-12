package com.yizhenwind.booster.order.category.ui.tab.subject

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
data class CategorySubjectViewState(
    val subjectList: PagingData<Subject> = PagingData.empty()
) : IViewState