package com.yizhenwind.booster.order.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.SubjectRepository
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
class CreateSubjectUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) : IUseCase {

    operator fun invoke(categoryId: Long, content: String): Flow<Subject?> =
        subjectRepository.createSubject(categoryId, content)

}