package com.yizhenwind.booster.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.booster.data.database.entity.SubjectEntity

/**
 * 代练项目表操作
 *
 * @author WangZhiYao
 * @since 2021/10/29
 */
@Dao
interface SubjectDao : IDao<SubjectEntity> {

    @Query("SELECT * FROM subject")
    fun observeSubjectList(): PagingSource<Int, SubjectEntity>

    @Query("SELECT * FROM subject WHERE category_id = :categoryId")
    fun observeSubjectListByCategoryId(categoryId: Long): PagingSource<Int, SubjectEntity>

    @Query("SELECT * FROM subject WHERE id = :subjectId")
    suspend fun getSubjectById(subjectId: Long): SubjectEntity?

}