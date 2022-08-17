package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.data.database.entity.CharacterEntity
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/13
 */
class CharacterToCharacterEntityMapper @Inject constructor() : IMapper<Character, CharacterEntity> {

    override fun map(input: Character) =
        input.run {
            CharacterEntity(
                id,
                customerId,
                zone.id,
                server.id,
                account,
                password,
                securityLock,
                name,
                sect.id,
                internal.id,
                remark,
                createTime
            )
        }
}