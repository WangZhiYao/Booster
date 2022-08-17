package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.data.database.entity.ZoneEntity
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class ZoneToZoneEntityMapper @Inject constructor() : IMapper<Zone, ZoneEntity> {

    override fun map(input: Zone) =
        input.run { ZoneEntity(id, name) }

}