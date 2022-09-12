package com.yizhenwind.booster.order.subject.data.domain

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.subject.data.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class ObserveSubjectListByCategoryIdUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) : IUseCase {

    operator fun invoke(categoryId: Long): Flow<PagingData<Subject>> =
        subjectRepository.observeSubjectListByCategoryId(categoryId)
}