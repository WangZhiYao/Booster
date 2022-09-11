package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Sect
import com.yizhenwind.booster.data.database.entity.SectEntity
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class SectToSectEntityMapper @Inject constructor() : IMapper<Sect, SectEntity> {

    override fun map(input: Sect) =
        input.run { SectEntity(id, name, iconUrl) }

}