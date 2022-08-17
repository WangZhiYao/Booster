package com.yizhenwind.booster.order.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.data.database.dao.SubjectDao
import com.yizhenwind.booster.data.database.entity.SubjectEntity
import com.yizhenwind.booster.data.database.mapper.SubjectEntityToSubjectMapper
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class SubjectRepository @Inject constructor(
    private val subjectDao: SubjectDao,
    private val subjectEntityToSubjectMapper: SubjectEntityToSubjectMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : IRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    fun observeSubjectListByCategoryId(categoryId: Long): Flow<PagingData<Subject>> =
        Pager(pagingConfig) {
            subjectDao.observeSubjectListByCategoryId(categoryId)
        }.flow
            .map { pagingData ->
                pagingData.map {
                    subjectEntityToSubjectMapper.map(it)
                }
            }
            .flowOn(dispatcher)

    fun createSubject(categoryId: Long, content: String): Flow<Subject?> =
        flow {
            emit(
                subjectDao.insert(
                    SubjectEntity(
                        categoryId = categoryId,
                        content = content
                    )
                )
            )
        }
            .map { subjectId ->
                subjectId?.let { subjectDao.getSubjectById(subjectId) }
            }
            .map { subjectEntity ->
                subjectEntity?.let { subjectEntityToSubjectMapper.map(it) }
            }
            .flowOn(dispatcher)
}