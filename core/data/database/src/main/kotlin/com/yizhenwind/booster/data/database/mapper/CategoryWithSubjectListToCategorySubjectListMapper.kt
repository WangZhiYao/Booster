package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.model.CategoryWithSubjectList
import com.yizhenwind.booster.model.CategorySubjectList
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

    override fun map(input: CategoryWithSubjectList) =
        input.run {
            CategorySubjectList(
                categoryEntityToCategoryMapper.map(category),
                ListMapper(subjectEntityToSubjectMapper).map(subjectList)
            )
        }

}