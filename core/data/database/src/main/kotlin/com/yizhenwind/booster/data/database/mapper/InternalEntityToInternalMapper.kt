package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.entity.InternalEntity
import com.yizhenwind.booster.model.Internal
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
class InternalEntityToInternalMapper @Inject constructor() : IMapper<InternalEntity, Internal> {

    override fun map(input: InternalEntity) =
        input.run { Internal(id, name, iconUrl) }

}