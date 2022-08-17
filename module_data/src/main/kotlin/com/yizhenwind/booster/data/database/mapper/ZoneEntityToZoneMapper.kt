package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.data.database.entity.ZoneEntity
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
class ZoneEntityToZoneMapper @Inject constructor(): IMapper<ZoneEntity, Zone> {

    override fun map(input: ZoneEntity) =
        input.run { Zone(id, name) }

}