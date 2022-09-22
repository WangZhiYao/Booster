package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 获取用户列表
 *
 * @author WangZhiYao
 * @since 2022/7/15
 */
class GetCustomerListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(): Flow<List<Customer>> = customerRepository.getCustomerList()
}