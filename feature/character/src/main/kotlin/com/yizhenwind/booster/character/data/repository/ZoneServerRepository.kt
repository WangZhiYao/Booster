package com.yizhenwind.booster.character.data.repository

import com.yizhenwind.booster.common.constant.PreferencesKey
import com.yizhenwind.booster.data.database.dao.ServerDao
import com.yizhenwind.booster.data.database.dao.ZoneDao
import com.yizhenwind.booster.data.database.mapper.ServerToServerEntityMapper
import com.yizhenwind.booster.data.database.mapper.ZoneToZoneEntityMapper
import com.yizhenwind.booster.data.database.mapper.ZoneWithServerListToZoneServerMapper
import com.yizhenwind.booster.data.datastore.DataStoreManager
import com.yizhenwind.booster.data.network.BoosterService
import com.yizhenwind.booster.framework.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.booster.logger.Logger
import com.yizhenwind.booster.model.ZoneServer
import com.yizhenwind.booster.model.ZoneServerList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 大区服务器
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class ZoneServerRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val zoneDao: ZoneDao,
    private val serverDao: ServerDao,
    private val zoneWithServerListToZoneServerMapper: ZoneWithServerListToZoneServerMapper,
    private val zoneToZoneEntityMapper: ZoneToZoneEntityMapper,
    private val boosterService: BoosterService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    /**
     * 获取大区服务器列表
     */
    fun getZoneServerList(): Flow<List<ZoneServer>> =
        flow {
            boosterService.getZoneServerList().apply {
                if (code == 200) {
                    dataStoreManager.getInt(PreferencesKey.VERSION_ZONE_SERVER, 0)
                        .collect { version ->
                            if (data.version > version) {
                                emit(data.zoneServerList)
                                updateLocalZoneServer(data)
                            }
                        }
                }
            }
        }
            .onStart {
                emit(
                    zoneDao.getZoneWithServerList().map {
                        zoneWithServerListToZoneServerMapper.map(it)
                    }
                )
            }
            .catch {
                Logger.e(it)
            }
            .flowOn(dispatcher)

    private suspend fun updateLocalZoneServer(zoneServerList: ZoneServerList) {
        zoneServerList.zoneServerList.forEach { zoneServer ->
            zoneDao.insertOrReplace(zoneToZoneEntityMapper.map(zoneServer.zone))
            serverDao.insertOrReplace(zoneServer.serverList.map { server ->
                ServerToServerEntityMapper(zoneServer.zone).map(server)
            })
        }

        dataStoreManager.setInt(PreferencesKey.VERSION_ZONE_SERVER, zoneServerList.version)
    }
}