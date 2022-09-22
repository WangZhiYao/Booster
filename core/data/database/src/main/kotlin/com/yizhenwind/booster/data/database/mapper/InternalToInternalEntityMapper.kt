package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.entity.InternalEntity
import com.yizhenwind.booster.model.Internal
import com.yizhenwind.booster.model.Sect

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class InternalToInternalEntityMapper(private val sect: Sect) : IMapper<Internal, InternalEntity> {

    override fun map(input: Internal) =
        input.run { InternalEntity(id, sect.id, name, iconUrl) }
}