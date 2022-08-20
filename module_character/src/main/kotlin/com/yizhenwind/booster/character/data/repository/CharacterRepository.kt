package com.yizhenwind.booster.character.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.booster.common.model.*
import com.yizhenwind.booster.data.database.dao.CharacterDao
import com.yizhenwind.booster.data.database.entity.CharacterEntity
import com.yizhenwind.booster.data.database.mapper.CharacterInfoToCharacterMapper
import com.yizhenwind.booster.data.database.mapper.CharacterToCharacterEntityMapper
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 角色
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterInfoToCharacterMapper: CharacterInfoToCharacterMapper,
    private val characterToCharacterEntityMapper: CharacterToCharacterEntityMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : IRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    /**
     * 根据客户ID分页订阅角色列表
     *
     * @param customerId 客户ID
     */
    fun observeCharacterListByCustomerId(customerId: Long): Flow<PagingData<Character>> =
        Pager(pagingConfig) {
            characterDao.observeCharacterListByCustomerId(customerId)
        }
            .flow
            .map { pagingData ->
                pagingData.map {
                    characterInfoToCharacterMapper.map(it)
                }
            }
            .flowOn(dispatcher)

    /**
     * 创建角色
     *
     * @param customerId   客户ID
     * @param zone         大区
     * @param server       服务器
     * @param account      账号
     * @param password     密码
     * @param securityLock 安全锁
     * @param name         角色名
     * @param sect         门派
     * @param internal     心法
     * @param remark       备注
     */
    fun createCharacter(
        customerId: Long,
        zone: Zone,
        server: Server,
        account: String,
        password: String,
        securityLock: String?,
        name: String,
        sect: Sect,
        internal: Internal,
        remark: String?
    ): Flow<Character?> =
        flow {
            emit(
                characterDao.insert(
                    CharacterEntity(
                        customerId = customerId,
                        zoneId = zone.id,
                        serverId = server.id,
                        account = account,
                        password = password,
                        securityLock = securityLock,
                        name = name,
                        sectId = sect.id,
                        internalId = internal.id,
                        remark = remark
                    )
                )
            )
        }
            .map { characterId ->
                characterId?.let { characterDao.getCharacterById(it) }
            }
            .map { characterInfo ->
                characterInfo?.let { characterInfoToCharacterMapper.map(it) }
            }
            .flowOn(dispatcher)

    /**
     * 根据ID获取角色
     *
     * @param characterId 角色ID
     */
    fun getCharacterById(characterId: Long): Flow<Character?> =
        flow {
            emit(characterDao.getCharacterById(characterId))
        }
            .map { characterInfo ->
                characterInfo?.let { characterInfoToCharacterMapper.map(it) }
            }
            .flowOn(dispatcher)

    /**
     * 删除角色
     *
     * @param character 需要删除的角色
     */
    fun deleteCharacter(character: Character): Flow<Boolean> =
        flow {
            emit(characterDao.delete(characterToCharacterEntityMapper.map(character)))
        }
            .catch {
                emit(0)
            }
            .map { count ->
                count >= 0
            }
            .flowOn(dispatcher)

}