package com.yizhenwind.booster.order.data.domain

import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.CategoryRepository
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
class GetCategoryListUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : IUseCase {

    suspend operator fun invoke(): List<Category> = categoryRepository.getCategoryList()
}