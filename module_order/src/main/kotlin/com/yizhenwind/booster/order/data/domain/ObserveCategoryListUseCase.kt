package com.yizhenwind.booster.order.data.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.CategoryRepository
import javax.inject.Inject

/**
 * 订阅分类列表
 *
 * @author WangZhiYao
 * @since 2022/7/20
 */
class ObserveCategoryListUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : IUseCase {

    operator fun invoke(): Flow<PagingData<Category>> = categoryRepository.observeCategoryList()
}