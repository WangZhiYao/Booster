package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.CategorySubjectList
import com.yizhenwind.booster.data.database.model.CategoryWithSubjectList
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/27
 */
class CategoryWithSubjectListToCategorySubjectListMapper @Inject constructor(
    private val categoryEntityToCategoryMapper: CategoryEntityToCategoryMapper,
    private val subjectEntityToSubjectMapper: SubjectEntityToSubjectMapper
) : IMapper<CategoryWithSubjectList, CategorySubjectList> {

    override fun invoke(input: CategoryWithSubjectList) =
        input.run {
            CategorySubjectList(
                categoryEntityToCategoryMapper(category),
                ListMapper(subjectEntityToSubjectMapper)(subjectList)
            )
        }

}