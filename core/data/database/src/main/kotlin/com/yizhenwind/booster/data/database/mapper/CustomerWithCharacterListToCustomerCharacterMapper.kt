package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.dto.CustomerWithCharacterListDto
import com.yizhenwind.booster.model.CustomerCharacterList
import javax.inject.Inject

/**
 * 客户角色映射器
 *
 * @author WangZhiYao
 * @since 2022/6/23
 */
class CustomerWithCharacterListToCustomerCharacterMapper @Inject constructor(
    private val customerEntityToCustomerMapper: CustomerEntityToCustomerMapper,
    private val zoneEntityToZoneMapper: ZoneEntityToZoneMapper,
    private val serverEntityToServerMapper: ServerEntityToServerMapper,
    private val sectEntityToSectMapper: SectEntityToSectMapper,
    private val internalEntityToInternalMapper: InternalEntityToInternalMapper
) : IMapper<CustomerWithCharacterListDto, CustomerCharacterList> {

    override fun map(input: CustomerWithCharacterListDto) =
        input.run {
            CustomerCharacterList(
                customerEntityToCustomerMapper.map(customer),
                ListMapper(CharacterInfoToCharacterMapper()).map(characterList)
            )
        }

    inner class CharacterInfoToCharacterMapper :
        IMapper<CustomerWithCharacterListDto.CharacterInfo, CustomerCharacterList.Character> {

        override fun map(input: CustomerWithCharacterListDto.CharacterInfo) =
            input.run {
                CustomerCharacterList.Character(
                    character.id,
                    character.name,
                    zoneEntityToZoneMapper.map(zoneEntity),
                    serverEntityToServerMapper.map(serverEntity),
                    sectEntityToSectMapper.map(sectEntity),
                    internalEntityToInternalMapper.map(internalEntity)
                )
            }
    }
}
