package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.data.database.model.CharacterInfo
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
class CharacterInfoToCharacterMapper @Inject constructor(
    private val zoneEntityToZoneMapper: ZoneEntityToZoneMapper,
    private val serverEntityToServerMapper: ServerEntityToServerMapper,
    private val sectEntityToSectMapper: SectEntityToSectMapper,
    private val internalEntityToInternalMapper: InternalEntityToInternalMapper
) : IMapper<CharacterInfo, Character> {

    override fun map(input: CharacterInfo) =
        input.run {
            Character(
                character.id,
                character.customerId,
                zoneEntityToZoneMapper.map(zoneEntity),
                serverEntityToServerMapper.map(serverEntity),
                character.account,
                character.password,
                character.securityLock,
                character.name,
                sectEntityToSectMapper.map(sectEntity),
                internalEntityToInternalMapper.map(internalEntity),
                character.remark,
                character.createTime
            )
        }
}