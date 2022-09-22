package com.yizhenwind.booster.character.data.domain

import com.yizhenwind.booster.character.data.repository.SectInternalRepository
import com.yizhenwind.booster.model.SectInternal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 获取门派心法列表
 *
 * @author WangZhiYao
 * @since 2022/6/3
 */
class GetSectInternalListUseCase @Inject constructor(
    private val sectInternalRepository: SectInternalRepository
) {

    operator fun invoke(): Flow<List<SectInternal>> =
        sectInternalRepository.getSectInternalList()
}