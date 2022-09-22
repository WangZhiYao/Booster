package com.yizhenwind.booster.character.data.domain

import com.yizhenwind.booster.character.data.repository.CharacterRepository
import com.yizhenwind.booster.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 创建角色
 *
 * @author WangZhiYao
 * @since 2022/6/4
 */
class CreateCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke(
        customerId: Long,
        zone: Zone,
        server: Server,
        account: String,
        password: String,
        securityLock: String?,
        characterName: String,
        sect: Sect,
        internal: Internal,
        remark: String?
    ): Flow<Character?> =
        characterRepository.createCharacter(
            customerId,
            zone,
            server,
            account,
            password,
            securityLock,
            characterName,
            sect,
            internal,
            remark
        )
}