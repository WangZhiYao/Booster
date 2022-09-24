package com.yizhenwind.booster.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.data.database.dto.CustomerSummaryDto
import com.yizhenwind.booster.data.database.dto.CustomerWithCharacterListDto
import com.yizhenwind.booster.data.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

/**
 * 客户表操作
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Dao
interface CustomerDao : IDao<CustomerEntity> {

    /**
     * 订阅查询所有客户
     */
    @Query("SELECT * FROM customer")
    fun observeCustomerList(): PagingSource<Int, CustomerEntity>

    /**
     * 查询所有客户, 按创建时间倒序排序
     */
    @Query("SELECT * FROM customer ORDER BY create_time DESC")
    suspend fun getCustomerList(): List<CustomerEntity>

    /**
     * 根据ID查询客户
     */
    @Query("SELECT * FROM customer WHERE id = :customerId")
    suspend fun getCustomerById(customerId: Long): CustomerEntity?

    /**
     * 根据具体的联系方式查询客户
     */
    @Query("SELECT * FROM customer WHERE contact_type = :contactType AND contact = :contact LIMIT 1")
    suspend fun getCustomerByContact(contactType: ContactType, contact: String): CustomerEntity?

    /**
     * 获取客户与其名下角色
     */
    @Transaction
    @Query("SELECT * FROM customer ORDER BY create_time DESC")
    suspend fun getCustomerWithCharacterList(): List<CustomerWithCharacterListDto>

    /**
     * 分页查询客户简介
     */
    @Query("SELECT id, name, contact_type, contact, (SELECT COUNT(*) FROM character WHERE customer_id = customer.id) as character_count, (SELECT COUNT(*) FROM `order` WHERE customer_id = customer.id ) AS order_count FROM customer")
    fun observeCustomerSummaryList(): PagingSource<Int, CustomerSummaryDto>

    /**
     * 根据ID删除客户
     */
    @Query("DELETE FROM customer WHERE id = :customerId")
    suspend fun deleteCustomerById(customerId: Long): Int

    /**
     * 根据ID订阅查询客户
     */
    @Query("SELECT * FROM customer WHERE id = :customerId")
    fun observeCustomerById(customerId: Long): Flow<CustomerEntity>
}