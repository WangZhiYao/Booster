package com.yizhenwind.booster.order.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.CategorySubjectList
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.order.data.repository.CategoryRepository
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/8
 */
class GetCategorySubjectListUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : IUseCase {

    operator fun invoke(): Flow<List<CategorySubjectList>> =
        categoryRepository.getCategorySubjectList()

}