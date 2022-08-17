package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Internal
import com.yizhenwind.booster.common.model.Sect
import com.yizhenwind.booster.data.database.entity.InternalEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class InternalToInternalEntityMapper(private val sect: Sect) : IMapper<Internal, InternalEntity> {

    override fun map(input: Internal) =
        input.run { InternalEntity(id, sect.id, name) }
}