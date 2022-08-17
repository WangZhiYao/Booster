package com.yizhenwind.booster.character.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.character.data.repository.SectInternalRepository
import com.yizhenwind.booster.common.model.SectInternal
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 获取门派心法列表
 *
 * @author WangZhiYao
 * @since 2022/6/3
 */
class GetSectInternalListUseCase @Inject constructor(
    private val sectInternalRepository: SectInternalRepository
) : IUseCase {

    operator fun invoke(): Flow<List<SectInternal>> = sectInternalRepository.getSectInternalList()
}