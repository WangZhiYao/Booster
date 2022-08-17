package com.yizhenwind.booster.customer.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.customer.data.repository.CustomerRepository
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 删除客户
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class DeleteCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) : IUseCase {

    operator fun invoke(customer: Customer): Flow<Boolean> =
        customerRepository.deleteCustomer(customer)
}