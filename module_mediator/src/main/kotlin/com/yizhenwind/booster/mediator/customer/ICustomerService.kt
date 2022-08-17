package com.yizhenwind.booster.mediator.customer

import android.content.Context
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.mediator.IService

/**
 * 用户模块对外接口
 *
 * @author WangZhiYao
 * @since 2022/7/14
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
    fun launchCustomerDetail(context: Context, customer: Customer)

    /**
     * 获取客户列表
     */
    fun getCustomerList(): Flow<List<Customer>>

    /**
     * 获取客户与角色列表
     */
    fun getCustomerWithCharacterList(): Flow<List<CustomerCharacterList>>
}