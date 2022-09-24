package com.yizhenwind.booster.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.booster.data.database.dto.CategoryWithSubjectListDto
import com.yizhenwind.booster.data.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * 分类表操作
 *
 * @author WangZhiYao
 * @since 2022/1/24
 */
@Dao
interface CategoryDao : IDao<CategoryEntity> {

    @Query("SELECT * FROM category")
    fun observeCategoryList(): PagingSource<Int, CategoryEntity>

    @Query("SELECT * FROM category")
    suspend fun getCategoryList(): List<CategoryEntity>

    @Transaction
    @Query("SELECT * FROM category")
    fun observeCategoryWithSubjectList(): Flow<List<CategoryWithSubjectListDto>>

    @Transaction
    @Query("SELECT * FROM category")
    fun getCategoryWithSubjectList(): List<CategoryWithSubjectListDto>

    @Query("SELECT * FROM category WHERE title = :title")
    suspend fun getCategoryByTitle(title: String): CategoryEntity?

    @Query("SELECT * FROM category WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Long): CategoryEntity?
}