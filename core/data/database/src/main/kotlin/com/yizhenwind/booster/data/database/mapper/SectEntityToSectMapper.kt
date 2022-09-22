package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.entity.SectEntity
import com.yizhenwind.booster.model.Sect
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
class SectEntityToSectMapper @Inject constructor() : IMapper<SectEntity, Sect> {

    override fun map(input: SectEntity) =
        input.run { Sect(id, name, iconUrl) }

}