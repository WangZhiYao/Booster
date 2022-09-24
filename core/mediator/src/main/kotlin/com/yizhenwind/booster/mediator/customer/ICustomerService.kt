package com.yizhenwind.booster.mediator.customer

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.booster.mediator.IService
import com.yizhenwind.booster.model.Customer
import com.yizhenwind.booster.model.CustomerCharacterList
import com.yizhenwind.booster.model.CustomerSummary
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/21
 */
interface ICustomerService : IService {

    /**
     * 启动创建客户页面
     */
    fun launchCreateCustomer(context: Context)

    /**
     * 启动客户详情页面
     *
     * @param customerId 客户ID
     */
    fun launchCustomerTab(context: Context, customerId: Long, tabIndex: Int)

    /**
     * 订阅客户列表
     */
    fun observeCustomerList(): Flow<PagingData<Customer>>

    /**
     * 订阅客户概要列表
     */
    fun observeCustomerSummaryList(): Flow<PagingData<CustomerSummary>>

    /**
     * 获取客户列表
     */
    fun getCustomerList(): Flow<List<Customer>>

    /**
     * 获取客户与角色列表
     */
    fun getCustomerWithCharacterList(): Flow<List<CustomerCharacterList>>

    /**
     * 根据客户ID删除客户
     */
    fun deleteCustomerById(customerId: Long): Flow<Boolean>
}