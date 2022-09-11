package com.yizhenwind.booster.character.data.repository

import com.yizhenwind.booster.common.constant.PreferencesKey
import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.common.model.ZoneServer
import com.yizhenwind.booster.common.model.ZoneServerList
import com.yizhenwind.booster.data.database.dao.ServerDao
import com.yizhenwind.booster.data.database.dao.ZoneDao
import com.yizhenwind.booster.data.database.entity.ServerEntity
import com.yizhenwind.booster.data.database.entity.ZoneEntity
import com.yizhenwind.booster.data.database.mapper.*
import com.yizhenwind.booster.data.datastore.DataStoreService
import com.yizhenwind.booster.data.network.BoosterService
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
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
    private val dataStoreService: DataStoreService,
    private val zoneDao: ZoneDao,
    private val serverDao: ServerDao,
    private val zoneWithServerListToZoneServerMapper: ZoneWithServerListToZoneServerMapper,
    private val zoneToZoneEntityMapper: ZoneToZoneEntityMapper,
    private val serverEntityToServerMapper: ServerEntityToServerMapper,
    private val boosterService: BoosterService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : IRepository {

    /**
     * 获取大区服务器列表
     */
    fun getZoneServerList(): Flow<List<ZoneServer>> =
        flow {
            boosterService.getZoneServerList().apply {
                if (code == 200) {
                    dataStoreService.getInt(PreferencesKey.VERSION_ZONE_SERVER, 0)
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
                        zoneWithServerListToZoneServerMapper(it)
                    }
                )
            }
            .flowOn(dispatcher)

    fun getServerByZone(zone: Zone): Flow<List<Server>> =
        flow {
            emit(serverDao.getServerListByZoneId(zone.id))
        }
            .map {
                ListMapper(serverEntityToServerMapper)(it)
            }
            .flowOn(dispatcher)

    private suspend fun updateLocalZoneServer(zoneServerList: ZoneServerList) {
        val zoneEntityList = ArrayList<ZoneEntity>()
        val serverEntityList = ArrayList<ServerEntity>()

        zoneServerList.zoneServerList.forEach { zoneServer ->
            zoneServer.apply {
                zoneEntityList.add(zoneToZoneEntityMapper(zone))
                serverEntityList.addAll(
                    serverList.map { server ->
                        ServerToServerEntityMapper(zone)(server)
                    }
                )
            }
        }

        zoneDao.insertBatchReplace(zoneEntityList)
        serverDao.insertBatchReplace(serverEntityList)

        dataStoreService.setInt(PreferencesKey.VERSION_ZONE_SERVER, zoneServerList.version)
    }
}