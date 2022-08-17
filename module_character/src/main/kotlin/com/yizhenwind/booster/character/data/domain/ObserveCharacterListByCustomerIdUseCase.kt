package com.yizhenwind.booster.character.data.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.character.data.repository.CharacterRepository
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 根据用户ID订阅角色列表
 *
 * @author WangZhiYao
 * @since 2022/6/4
 */
class ObserveCharacterListByCustomerIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : IUseCase {

    operator fun invoke(customerId: Long): Flow<PagingData<Character>> =
        characterRepository.observeCharacterListByCustomerId(customerId)

}