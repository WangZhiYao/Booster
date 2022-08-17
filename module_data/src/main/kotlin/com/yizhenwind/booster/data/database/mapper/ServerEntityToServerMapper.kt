package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.data.database.entity.ServerEntity
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
class ServerEntityToServerMapper @Inject constructor() : IMapper<ServerEntity, Server> {

    override fun map(input: ServerEntity) =
        input.run { Server(id, name) }

}