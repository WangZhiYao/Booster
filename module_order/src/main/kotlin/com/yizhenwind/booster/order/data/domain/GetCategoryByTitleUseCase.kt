package com.yizhenwind.booster.order.data.domain

import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.CategoryRepository
import javax.inject.Inject

/**
 * 根据标题获取分类
 *
 * @author WangZhiYao
 * @since 2022/7/21
 */
class GetCategoryByTitleUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : IUseCase {

    suspend operator fun invoke(title: String): Category? =
        categoryRepository.getCategoryByTitle(title)

}