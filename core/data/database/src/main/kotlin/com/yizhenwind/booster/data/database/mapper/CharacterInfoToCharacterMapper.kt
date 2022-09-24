package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.dto.CharacterInfoDto
import com.yizhenwind.booster.model.Character
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
) : IMapper<CharacterInfoDto, Character> {

    override fun map(input: CharacterInfoDto) =
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