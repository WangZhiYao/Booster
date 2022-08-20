package com.yizhenwind.booster.character.data.domain

import com.yizhenwind.booster.character.data.repository.CharacterRepository
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.data.domain.IUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : IUseCase {
    operator fun invoke(characterId: Long): Flow<Character?> =
        characterRepository.getCharacterById(characterId)
}