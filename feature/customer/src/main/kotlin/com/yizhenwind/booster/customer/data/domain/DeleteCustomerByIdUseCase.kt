package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.customer.data.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/24
 */
class DeleteCustomerByIdUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(customerId: Long): Flow<Boolean> =
        customerRepository.deleteCustomerById(customerId)

}