package com.yizhenwind.booster.customer.data.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.customer.data.repository.CustomerRepository
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 订阅客户列表
 *
 * @author WangZhiYao
 * @since 2022/5/22
 */
class ObserveCustomerListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) : IUseCase {

    operator fun invoke(): Flow<PagingData<Customer>> = customerRepository.observeCustomerList()

}