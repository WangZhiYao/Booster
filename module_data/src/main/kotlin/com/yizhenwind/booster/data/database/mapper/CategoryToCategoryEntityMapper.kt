package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.data.database.entity.CategoryEntity
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class CategoryToCategoryEntityMapper @Inject constructor() : IMapper<Category, CategoryEntity> {

    override fun invoke(input: Category): CategoryEntity = input.run {
        CategoryEntity(id, title, description, createTime)
    }
}