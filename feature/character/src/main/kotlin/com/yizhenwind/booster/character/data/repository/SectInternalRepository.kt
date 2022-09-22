package com.yizhenwind.booster.character.data.repository

import com.yizhenwind.booster.common.constant.PreferencesKey
import com.yizhenwind.booster.data.database.dao.InternalDao
import com.yizhenwind.booster.data.database.dao.SectDao
import com.yizhenwind.booster.data.database.mapper.InternalToInternalEntityMapper
import com.yizhenwind.booster.data.database.mapper.SectToSectEntityMapper
import com.yizhenwind.booster.data.database.mapper.SectWithInternalListToSectInternalMapper
import com.yizhenwind.booster.data.datastore.DataStoreManager
import com.yizhenwind.booster.data.network.BoosterService
import com.yizhenwind.booster.framework.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.booster.logger.Logger
import com.yizhenwind.booster.model.SectInternal
import com.yizhenwind.booster.model.SectInternalList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 门派心法
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class SectInternalRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val sectDao: SectDao,
    private val internalDao: InternalDao,
    private val sectWithInternalListToSectInternalMapper: SectWithInternalListToSectInternalMapper,
    private val sectToSectEntityMapper: SectToSectEntityMapper,
    private val boosterService: BoosterService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    /**
     * 获取门派心法列表
     */
    fun getSectInternalList(): Flow<List<SectInternal>> =
        flow {
            boosterService.getSectInternalList().apply {
                if (code == 200) {
                    dataStoreManager.getInt(PreferencesKey.VERSION_SECT_INTERNAL, 0)
                        .collect { version ->
                            if (data.version > version) {
                                emit(data.sectInternalList)
                                updateLocalSectInternal(data)
                            }
                        }
                }
            }
        }
            .onStart {
                emit(
                    sectDao.getSectWithInternalList().map {
                        sectWithInternalListToSectInternalMapper.map(it)
                    }
                )
            }
            .catch {
                Logger.e(it)
            }
            .flowOn(dispatcher)

    private suspend fun updateLocalSectInternal(sectInternalList: SectInternalList) {
        sectInternalList.sectInternalList.forEach { sectInternal ->
            sectDao.insertOrReplace(sectToSectEntityMapper.map(sectInternal.sect))
            internalDao.insertOrReplace(sectInternal.internalList.map { internal ->
                InternalToInternalEntityMapper(sectInternal.sect).map(internal)
            })
        }

        dataStoreManager.setInt(PreferencesKey.VERSION_SECT_INTERNAL, sectInternalList.version)
    }
}