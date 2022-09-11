package com.yizhenwind.booster.character.data.repository

import com.yizhenwind.booster.common.constant.PreferencesKey
import com.yizhenwind.booster.common.model.SectInternal
import com.yizhenwind.booster.common.model.SectInternalList
import com.yizhenwind.booster.data.database.dao.InternalDao
import com.yizhenwind.booster.data.database.dao.SectDao
import com.yizhenwind.booster.data.database.entity.InternalEntity
import com.yizhenwind.booster.data.database.entity.SectEntity
import com.yizhenwind.booster.data.database.mapper.InternalToInternalEntityMapper
import com.yizhenwind.booster.data.database.mapper.SectToSectEntityMapper
import com.yizhenwind.booster.data.database.mapper.SectWithInternalListToSectInternalMapper
import com.yizhenwind.booster.data.datastore.DataStoreService
import com.yizhenwind.booster.data.network.BoosterService
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

/**
 * 门派心法
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class SectInternalRepository @Inject constructor(
    private val dataStoreService: DataStoreService,
    private val sectDao: SectDao,
    private val internalDao: InternalDao,
    private val sectWithInternalListToSectInternalMapper: SectWithInternalListToSectInternalMapper,
    private val sectToSectEntityMapper: SectToSectEntityMapper,
    private val boosterService: BoosterService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : IRepository {

    /**
     * 获取门派心法列表
     */
    fun getSectInternalList(): Flow<List<SectInternal>> =
        flow {
            boosterService.getSectInternalList().apply {
                if (code == 200) {
                    dataStoreService.getInt(PreferencesKey.VERSION_SECT_INTERNAL, 0)
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
                        sectWithInternalListToSectInternalMapper(it)
                    }
                )
            }
            .catch {
                Timber.e(it)
            }
            .flowOn(dispatcher)

    private suspend fun updateLocalSectInternal(sectInternalList: SectInternalList) {
        val sectEntityList = ArrayList<SectEntity>()
        val internalEntityList = ArrayList<InternalEntity>()

        sectInternalList.sectInternalList.forEach { sectInternal ->
            sectInternal.apply {
                sectEntityList.add(sectToSectEntityMapper(sect))
                internalEntityList.addAll(internalList.map { internal ->
                    InternalToInternalEntityMapper(sect)(internal)
                })
            }
        }

        sectDao.insertBatchReplace(sectEntityList)
        internalDao.insertBatchReplace(internalEntityList)

        dataStoreService.setInt(PreferencesKey.VERSION_SECT_INTERNAL, sectInternalList.version)
    }
}