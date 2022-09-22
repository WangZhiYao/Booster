package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.CustomerCharacterList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 获取客户与角色列表
 *
 * @author WangZhiYao
 * @since 2022/6/30
 */
class GetCustomerWithCharacterListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(): Flow<List<CustomerCharacterList>> =
        customerRepository.getCustomerWithCharacterList()
}