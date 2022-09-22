package com.yizhenwind.booster.mediator.customer

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.booster.mediator.IService
import com.yizhenwind.booster.model.Customer
import com.yizhenwind.booster.model.CustomerCharacterList
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
     * @param customer 客户
     */
    fun launchCustomerTab(context: Context, customer: Customer, tabIndex: Int)

    /**
     * 订阅客户列表
     */
    fun observeCustomerList(): Flow<PagingData<Customer>>

    /**
     * 获取客户列表
     */
    fun getCustomerList(): Flow<List<Customer>>

    /**
     * 获取客户与角色列表
     */
    fun getCustomerWithCharacterList(): Flow<List<CustomerCharacterList>>

}