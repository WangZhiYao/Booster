package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.data.database.entity.SubjectEntity
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class SubjectEntityToSubjectMapper @Inject constructor() : IMapper<SubjectEntity, Subject> {

    override fun map(input: SubjectEntity): Subject =
        input.run {
            Subject(
                id,
                categoryId,
                content,
                createTime
            )
        }
}