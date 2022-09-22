package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.entity.InternalEntity
import com.yizhenwind.booster.data.database.model.SectWithInternalList
import com.yizhenwind.booster.model.Internal
import com.yizhenwind.booster.model.SectInternal
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/4/26
 */
class SectWithInternalListToSectInternalMapper @Inject constructor(
    private val sectEntityToSectMapper: SectEntityToSectMapper,
    internalEntityToInternalMapper: InternalEntityToInternalMapper
) : IMapper<SectWithInternalList, SectInternal> {

    private val internalEntityListToInternalListMapper: ListMapper<InternalEntity, Internal> =
        ListMapper(internalEntityToInternalMapper)

    override fun map(input: SectWithInternalList) =
        input.run {
            SectInternal(
                sectEntityToSectMapper.map(input.sect),
                internalEntityListToInternalListMapper.map(input.internalList)
            )
        }
}