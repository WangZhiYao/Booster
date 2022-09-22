package com.yizhenwind.booster.character.data.domain

import com.yizhenwind.booster.character.data.repository.ZoneServerRepository
import com.yizhenwind.booster.model.ZoneServer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 获取大区与服务器
 *
 * @author WangZhiYao
 * @since 2022/6/3
 */
class GetZoneServerListUseCase @Inject constructor(
    private val zoneServerRepository: ZoneServerRepository
) {

    operator fun invoke(): Flow<List<ZoneServer>> = zoneServerRepository.getZoneServerList()
}