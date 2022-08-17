package com.yizhenwind.booster.character.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.character.data.repository.CharacterRepository
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 删除角色
 *
 * @author WangZhiYao
 * @since 2022/6/13
 */
class DeleteCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : IUseCase {

    operator fun invoke(character: Character): Flow<Boolean> =
        characterRepository.deleteCharacter(character)

}