package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/24
 */
class ObserveCustomerByIdUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(customerId: Long): Flow<Customer> =
        customerRepository.observeCustomerById(customerId)

}