package com.yizhenwind.booster.customer.service

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.booster.customer.data.domain.*
import com.yizhenwind.booster.customer.ui.create.CreateCustomerArgs
import com.yizhenwind.booster.customer.ui.tab.CustomerTabArgs
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.model.Customer
import com.yizhenwind.booster.model.CustomerCharacterList
import com.yizhenwind.booster.model.CustomerSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 用户模块对外接口实现类
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
class CustomerServiceImpl @Inject constructor(
    private val observeCustomerListUseCase: ObserveCustomerListUseCase,
    private val observeCustomerSummaryListUseCase: ObserveCustomerSummaryListUseCase,
    private val getCustomerListUseCase: GetCustomerListUseCase,
    private val getCustomerWithCharacterListUseCase: GetCustomerWithCharacterListUseCase,
    private val deleteCustomerByIdUseCase: DeleteCustomerByIdUseCase
) : ICustomerService {

    override fun launchCreateCustomer(context: Context) {
        CreateCustomerArgs().launch(context)
    }

    override fun launchCustomerTab(
        context: Context,
        customerId: Long,
        tabIndex: Int
    ) {
        CustomerTabArgs(customerId, tabIndex).launch(context)
    }

    override fun observeCustomerList(): Flow<PagingData<Customer>> = observeCustomerListUseCase()

    override fun observeCustomerSummaryList(): Flow<PagingData<CustomerSummary>> =
        observeCustomerSummaryListUseCase()

    override fun getCustomerList(): Flow<List<Customer>> = getCustomerListUseCase()

    override fun getCustomerWithCharacterList(): Flow<List<CustomerCharacterList>> =
        getCustomerWithCharacterListUseCase()

    override fun deleteCustomerById(customerId: Long): Flow<Boolean> =
        deleteCustomerByIdUseCase(customerId)
}