package com.yizhenwind.booster.customer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.data.database.dao.CustomerDao
import com.yizhenwind.booster.data.database.entity.CustomerEntity
import com.yizhenwind.booster.data.database.mapper.CustomerEntityToCustomerMapper
import com.yizhenwind.booster.data.database.mapper.CustomerToCustomerEntityMapper
import com.yizhenwind.booster.data.database.mapper.CustomerWithCharacterListToCustomerCharacterMapper
import com.yizhenwind.booster.data.database.mapper.ListMapper
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
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
) : IRepository {

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
}