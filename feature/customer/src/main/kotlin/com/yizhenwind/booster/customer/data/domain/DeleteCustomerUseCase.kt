package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 删除客户
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class DeleteCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(customer: Customer): Flow<Boolean> =
        customerRepository.deleteCustomer(customer)
}