package com.yizhenwind.booster.order.category.data.repository

import com.yizhenwind.booster.data.database.dao.CategoryDao
import com.yizhenwind.booster.data.repository.IRepository
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) : IRepository