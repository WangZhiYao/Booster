package com.yizhenwind.booster.character.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import com.yizhenwind.booster.common.model.ZoneServer
import com.yizhenwind.booster.common.model.ZoneServerList
import com.yizhenwind.booster.common.constant.PreferencesKey
import com.yizhenwind.booster.data.database.dao.ServerDao
import com.yizhenwind.booster.data.database.dao.ZoneDao
import com.yizhenwind.booster.data.database.mapper.ServerToServerEntityMapper
import com.yizhenwind.booster.data.database.mapper.ZoneToZoneEntityMapper
import com.yizhenwind.booster.data.database.mapper.ZoneWithServerListToZoneServerMapper
import com.yizhenwind.booster.data.datastore.DataStoreService
import com.yizhenwind.booster.data.network.BoosterService
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import timber.log.Timber
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
                        zoneWithServerListToZoneServerMapper.map(it)
                    }
                )
            }
            .catch {
                Timber.e(it)
            }
            .flowOn(dispatcher)

    private suspend fun updateLocalZoneServer(zoneServerList: ZoneServerList) {
        zoneServerList.zoneServerList.forEach { zoneServer ->
            zoneDao.insertReplace(zoneToZoneEntityMapper.map(zoneServer.zone))
            serverDao.insertBatchReplace(zoneServer.serverList.map { server ->
                ServerToServerEntityMapper(zoneServer.zone).map(server)
            })
        }

        dataStoreService.setInt(PreferencesKey.VERSION_ZONE_SERVER, zoneServerList.version)
    }
}