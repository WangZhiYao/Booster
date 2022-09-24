package com.yizhenwind.booster.customer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.data.database.dao.CustomerDao
import com.yizhenwind.booster.data.database.dto.CustomerSummaryDto
import com.yizhenwind.booster.data.database.entity.CustomerEntity
import com.yizhenwind.booster.data.database.mapper.CustomerEntityToCustomerMapper
import com.yizhenwind.booster.data.database.mapper.CustomerToCustomerEntityMapper
import com.yizhenwind.booster.data.database.mapper.CustomerWithCharacterListToCustomerCharacterMapper
import com.yizhenwind.booster.data.database.mapper.ListMapper
import com.yizhenwind.booster.framework.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.booster.model.Customer
import com.yizhenwind.booster.model.CustomerCharacterList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/28
 */
class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao,
    private val customerEntityToCustomerMapper: CustomerEntityToCustomerMapper,
    private val customerToCustomerEntityMapper: CustomerToCustomerEntityMapper,
    private val customerWithCharacterListToCustomerCharacterMapper: CustomerWithCharacterListToCustomerCharacterMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    /**
     * 分页订阅客户列表
     */
    fun observeCustomerList(): Flow<PagingData<Customer>> =
        Pager(pagingConfig) {
            customerDao.observeCustomerList()
        }
            .flow
            .map { pagingData ->
                pagingData.map {
                    customerEntityToCustomerMapper.map(it)
                }
            }
            .flowOn(dispatcher)

    fun observeCustomerSummaryList(): Flow<PagingData<CustomerSummaryDto>> =
        Pager(pagingConfig) {
            customerDao.observeCustomerSummaryList()
        }
            .flow
            .flowOn(dispatcher)

    /**
     * 创建客户
     *
     * @param name        客户名
     * @param contactType 联系方式类型
     * @param contact     联系方式
     * @param remark      备注
     */
    fun createCustomer(
        name: String,
        contactType: ContactType,
        contact: String,
        remark: String?
    ): Flow<Customer?> =
        flow {
            emit(
                customerDao.insert(
                    CustomerEntity(
                        name = name,
                        contactType = contactType,
                        contact = contact,
                        remark = remark
                    )
                )
            )
        }
            .map { customerId ->
                customerId?.let { customerDao.getCustomerById(it) }
            }
            .map { customerEntity ->
                customerEntity?.let { customerEntityToCustomerMapper.map(it) }
            }
            .flowOn(dispatcher)

    /**
     * 根据ID查询客户
     *
     * @param customerId 客户ID
     */
    fun getCustomerById(customerId: Long): Flow<Customer?> =
        flow {
            emit(customerDao.getCustomerById(customerId))
        }
            .map { customerEntity ->
                customerEntity?.let { customerEntityToCustomerMapper.map(it) }
            }
            .flowOn(dispatcher)

    /**
     * 根据联系方式类型与联系方式查找客户
     *
     * @param contactType 联系方式类型
     * @param contact     联系方式
     */
    suspend fun getCustomerByContact(
        contactType: ContactType,
        contact: String
    ): Customer? =
        customerDao.getCustomerByContact(contactType, contact)
            ?.let { customerEntityToCustomerMapper.map(it) }

    /**
     * 获取客户列表
     */
    fun getCustomerList(): Flow<List<Customer>> =
        flow {
            emit(customerDao.getCustomerList())
        }
            .map {
                ListMapper(customerEntityToCustomerMapper).map(it)
            }
            .flowOn(dispatcher)

    /**
     * 获取客户与角色列表
     */
    fun getCustomerWithCharacterList(): Flow<List<CustomerCharacterList>> =
        flow {
            emit(customerDao.getCustomerWithCharacterList())
        }
            .map {
                ListMapper(customerWithCharacterListToCustomerCharacterMapper).map(it)
            }
            .flowOn(dispatcher)

    /**
     * 删除客户
     *
     * @param customer 客户
     */
    fun deleteCustomer(customer: Customer): Flow<Boolean> =
        flow {
            emit(customerDao.delete(customerToCustomerEntityMapper.map(customer)))
        }
            .catch {
                emit(0)
            }
            .map { count ->
                count >= 0
            }
            .flowOn(dispatcher)

    fun deleteCustomerById(customerId: Long): Flow<Boolean> =
        flow {
            emit(customerDao.deleteCustomerById(customerId))
        }
            .catch {
                emit(0)
            }
            .map { count ->
                count >= 0
            }
            .flowOn(dispatcher)

    fun observeCustomerById(customerId: Long): Flow<Customer> =
        customerDao.observeCustomerById(customerId)
            .map {
                customerEntityToCustomerMapper.map(it)
            }
            .flowOn(dispatcher)
}