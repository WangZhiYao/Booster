package com.yizhenwind.booster.customer.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.customer.data.repository.CustomerRepository
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 获取用户列表
 *
 * @author WangZhiYao
 * @since 2022/7/15
 */
class GetCustomerListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) : IUseCase {

    operator fun invoke(): Flow<List<Customer>> = customerRepository.getCustomerList()
}