package com.yizhenwind.booster.customer.service

import android.content.Context
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.customer.data.domain.GetCustomerListUseCase
import com.yizhenwind.booster.customer.data.domain.GetCustomerWithCharacterListUseCase
import com.yizhenwind.booster.customer.ui.create.CreateCustomerLaunchArgs
import com.yizhenwind.booster.customer.ui.detail.CustomerDetailLaunchArgs
import com.yizhenwind.booster.mediator.customer.ICustomerService
import javax.inject.Inject

/**
 * 用户模块对外接口实现类
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
class CustomerServiceImpl @Inject constructor(
    private val getCustomerListUseCase: GetCustomerListUseCase,
    private val getCustomerWithCharacterListUseCase: GetCustomerWithCharacterListUseCase
) : ICustomerService {

    override fun launchCreateCustomer(context: Context) {
        CreateCustomerLaunchArgs().launch(context)
    }

    override fun launchCustomerDetail(context: Context, customer: Customer) {
        CustomerDetailLaunchArgs(customer).launch(context)
    }

    override fun getCustomerList(): Flow<List<Customer>> = getCustomerListUseCase()

    override fun getCustomerWithCharacterList(): Flow<List<CustomerCharacterList>> =
        getCustomerWithCharacterListUseCase()
}