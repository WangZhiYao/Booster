package com.yizhenwind.booster.character.data.domain

import com.yizhenwind.booster.character.data.repository.CharacterRepository
import com.yizhenwind.booster.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 删除角色
 *
 * @author WangZhiYao
 * @since 2022/6/13
 */
class DeleteCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke(character: Character): Flow<Boolean> =
        characterRepository.deleteCharacter(character)

}