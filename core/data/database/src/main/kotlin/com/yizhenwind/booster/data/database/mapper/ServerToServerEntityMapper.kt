package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.entity.ServerEntity
import com.yizhenwind.booster.model.Server
import com.yizhenwind.booster.model.Zone

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class ServerToServerEntityMapper(private val zone: Zone) : IMapper<Server, ServerEntity> {

    override fun map(input: Server) =
        input.run { ServerEntity(id, zone.id, name) }

}