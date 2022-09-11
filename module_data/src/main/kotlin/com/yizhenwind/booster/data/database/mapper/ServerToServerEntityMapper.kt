package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.data.database.entity.ServerEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class ServerToServerEntityMapper(private val zone: Zone) : IMapper<Server, ServerEntity> {

    override fun invoke(input: Server) =
        input.run { ServerEntity(id, zone.id, name) }

}