package com.yizhenwind.booster.customer.data.domain

import androidx.paging.PagingData
import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 订阅客户列表
 *
 * @author WangZhiYao
 * @since 2022/5/22
 */
class ObserveCustomerListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(): Flow<PagingData<Customer>> =
        customerRepository.observeCustomerList()

}