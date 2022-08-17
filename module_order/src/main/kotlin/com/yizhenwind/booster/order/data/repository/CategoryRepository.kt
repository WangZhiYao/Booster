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
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.common.model.CategorySubjectList
import com.yizhenwind.booster.data.database.dao.CategoryDao
import com.yizhenwind.booster.data.database.entity.CategoryEntity
import com.yizhenwind.booster.data.database.mapper.CategoryEntityToCategoryMapper
import com.yizhenwind.booster.data.database.mapper.CategoryToCategoryEntityMapper
import com.yizhenwind.booster.data.database.mapper.CategoryWithSubjectListToCategorySubjectListMapper
import com.yizhenwind.booster.data.database.mapper.ListMapper
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/3/8
 */
class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoryEntityToCategoryMapper: CategoryEntityToCategoryMapper,
    private val categoryToCategoryEntityMapper: CategoryToCategoryEntityMapper,
    private val categoryWithSubjectListToCategorySubjectListMapper: CategoryWithSubjectListToCategorySubjectListMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : IRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    /**
     * 订阅分类列表
     */
    fun observeCategoryList(): Flow<PagingData<Category>> =
        Pager(pagingConfig) {
            categoryDao.observeCategoryList()
        }
            .flow
            .map { pagingData ->
                pagingData.map {
                    categoryEntityToCategoryMapper.map(it)
                }
            }
            .flowOn(dispatcher)

    /**
     * 获取分类列表
     */
    suspend fun getCategoryList(): List<Category> =
        categoryDao.getCategoryList().map { categoryEntityToCategoryMapper.map(it) }

    /**
     * 通过标题获取分类
     *
     * @param title 标题
     */
    suspend fun getCategoryByTitle(title: String): Category? =
        categoryDao.getCategoryByTitle(title)?.let { categoryEntityToCategoryMapper.map(it) }

    /**
     * 创建分类
     *
     * @param title       标题
     * @param description 描述
     */
    fun createCategory(title: String, description: String?): Flow<Category?> =
        flow {
            emit(categoryDao.insert(CategoryEntity(title = title, description = description)))
        }
            .map { categoryId ->
                categoryId?.let { categoryDao.getCategoryById(it) }
            }
            .map { categoryEntity ->
                categoryEntity?.let { categoryEntityToCategoryMapper.map(it) }
            }
            .flowOn(dispatcher)

    /**
     * 订阅分类与项目列表
     */
    fun observeCategorySubjectList(): Flow<List<CategorySubjectList>> =
        categoryDao.observeCategoryWithSubjectList()
            .map {
                ListMapper(categoryWithSubjectListToCategorySubjectListMapper).map(it)
            }
            .flowOn(dispatcher)

    /**
     * 获取分类与项目列表
     */
    fun getCategorySubjectList(): Flow<List<CategorySubjectList>> =
        flow {
            emit(categoryDao.getCategoryWithSubjectList())
        }
            .map {
                ListMapper(categoryWithSubjectListToCategorySubjectListMapper).map(it)
            }
            .flowOn(dispatcher)

    /**
     * 删除分类
     *
     * @param category 需要删除的分类
     */
    fun deleteCategory(category: Category): Flow<Boolean> =
        flow {
            emit(categoryDao.delete(categoryToCategoryEntityMapper.map(category)))
        }
            .map { count ->
                count >= 0
            }
            .flowOn(dispatcher)
}