package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.common.model.ZoneServer
import com.yizhenwind.booster.data.database.entity.ServerEntity
import com.yizhenwind.booster.data.database.model.ZoneWithServerList
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/4/26
 */
class ZoneWithServerListToZoneServerMapper @Inject constructor(
    private val zoneEntityToZoneMapper: ZoneEntityToZoneMapper,
    serverEntityToServerMapper: ServerEntityToServerMapper
) : IMapper<ZoneWithServerList, ZoneServer> {

    private val serverEntityListToServerListMapper: ListMapper<ServerEntity, Server> =
        ListMapper(serverEntityToServerMapper)

    override fun invoke(input: ZoneWithServerList) =
        input.run {
            ZoneServer(
                zoneEntityToZoneMapper(zone),
                serverEntityListToServerListMapper(serverList)
            )
        }

}