package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.dto.ZoneWithServerListDto
import com.yizhenwind.booster.data.database.entity.ServerEntity
import com.yizhenwind.booster.model.Server
import com.yizhenwind.booster.model.ZoneServer
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/4/26
 */
class ZoneWithServerListToZoneServerMapper @Inject constructor(
    private val zoneEntityToZoneMapper: ZoneEntityToZoneMapper,
    serverEntityToServerMapper: ServerEntityToServerMapper
) : IMapper<ZoneWithServerListDto, ZoneServer> {

    private val serverEntityListToServerListMapper: ListMapper<ServerEntity, Server> =
        ListMapper(serverEntityToServerMapper)

    override fun map(input: ZoneWithServerListDto) =
        input.run {
            ZoneServer(
                zoneEntityToZoneMapper.map(zone),
                serverEntityListToServerListMapper.map(serverList)
            )
        }

}