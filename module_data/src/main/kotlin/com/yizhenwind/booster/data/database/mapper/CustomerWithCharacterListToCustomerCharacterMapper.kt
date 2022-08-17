package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.data.database.model.CustomerWithCharacterList
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
) : IMapper<CustomerWithCharacterList, CustomerCharacterList> {

    override fun map(input: CustomerWithCharacterList) =
        input.run {
            CustomerCharacterList(
                customerEntityToCustomerMapper.map(customer),
                ListMapper(CharacterInfoToCharacterMapper()).map(characterList)
            )
        }

    inner class CharacterInfoToCharacterMapper :
        IMapper<CustomerWithCharacterList.CharacterInfo, CustomerCharacterList.Character> {

        override fun map(input: CustomerWithCharacterList.CharacterInfo) =
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
