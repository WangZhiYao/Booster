package com.yizhenwind.booster.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.booster.data.database.entity.OrderEntity
import com.yizhenwind.booster.data.database.model.OrderInfo

/**
 * 订单表操作
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Dao
interface OrderDao : IDao<OrderEntity> {

    @Transaction
    @Query("SELECT * FROM `order` WHERE customer_id = :customerId")
    fun observeOrderListByCustomerId(customerId: Long): PagingSource<Int, OrderInfo>

    @Transaction
    @Query("SELECT * FROM `order` WHERE character_id = :characterId")
    fun observeOrderListByCharacterId(characterId: Long): PagingSource<Int, OrderInfo>

}