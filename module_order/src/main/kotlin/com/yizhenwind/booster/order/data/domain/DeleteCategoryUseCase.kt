package com.yizhenwind.booster.order.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.CategoryRepository
import javax.inject.Inject

/**
 * 删除分类
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class DeleteCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : IUseCase {

    operator fun invoke(category: Category): Flow<Boolean> =
        categoryRepository.deleteCategory(category)

}