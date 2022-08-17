package com.yizhenwind.booster.order.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.CategoryRepository
import javax.inject.Inject

/**
 * 创建类别
 *
 * @author WangZhiYao
 * @since 2022/7/21
 */
class CreateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : IUseCase {

    operator fun invoke(title: String, description: String?): Flow<Category?> =
        categoryRepository.createCategory(title, description)

}