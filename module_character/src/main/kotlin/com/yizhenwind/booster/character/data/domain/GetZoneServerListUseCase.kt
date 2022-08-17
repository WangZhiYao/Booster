package com.yizhenwind.booster.character.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.character.data.repository.ZoneServerRepository
import com.yizhenwind.booster.common.model.ZoneServer
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 获取大区与服务器
 *
 * @author WangZhiYao
 * @since 2022/6/3
 */
class GetZoneServerListUseCase @Inject constructor(
    private val zoneServerRepository: ZoneServerRepository
) : IUseCase {

    operator fun invoke(): Flow<List<ZoneServer>> = zoneServerRepository.getZoneServerList()
}