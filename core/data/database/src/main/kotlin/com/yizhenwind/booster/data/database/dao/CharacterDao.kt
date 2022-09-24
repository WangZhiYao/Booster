package com.yizhenwind.booster.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.booster.data.database.dto.CharacterInfoDto
import com.yizhenwind.booster.data.database.entity.CharacterEntity

/**
 * 角色表操作
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Dao
interface CharacterDao : IDao<CharacterEntity> {

    @Transaction
    @Query("SELECT * FROM character WHERE customer_id = :customerId ORDER BY create_time DESC")
    fun observeCharacterListByCustomerId(customerId: Long): PagingSource<Int, CharacterInfoDto>

    @Transaction
    @Query("SELECT * FROM character WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Long): CharacterInfoDto?

}