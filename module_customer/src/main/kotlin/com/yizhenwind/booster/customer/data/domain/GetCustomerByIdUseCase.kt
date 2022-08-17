package com.yizhenwind.booster.customer.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.customer.data.repository.CustomerRepository
import javax.inject.Inject

/**
 * 根据ID查找客户
 *
 * @author WangZhiYao
 * @since 2022/7/28
 */
class GetCustomerByIdUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(customerId: Long): Flow<Customer?> =
        customerRepository.getCustomerById(customerId)
}