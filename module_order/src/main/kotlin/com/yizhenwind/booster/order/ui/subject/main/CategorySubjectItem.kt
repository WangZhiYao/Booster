package com.yizhenwind.booster.order.ui.subject.main

import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.common.model.Subject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/5
 */
sealed class CategorySubjectItem {

    data class CategoryItem(val category: Category) : CategorySubjectItem()

    object CategoryEmptyItem : CategorySubjectItem()

    data class SubjectItem(val subject: Subject) : CategorySubjectItem()

}
