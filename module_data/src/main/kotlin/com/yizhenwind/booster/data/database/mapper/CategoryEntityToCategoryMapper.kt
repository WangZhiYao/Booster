package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.data.database.entity.CategoryEntity
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/7
 */
class CategoryEntityToCategoryMapper @Inject constructor() : IMapper<CategoryEntity, Category> {

    override fun map(input: CategoryEntity) =
        input.run { Category(id, title, description, createTime) }

}