package com.yizhenwind.booster.customer.data.domain

import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.customer.data.repository.CustomerRepository
import com.yizhenwind.booster.data.domain.IUseCase
import javax.inject.Inject

/**
 * 获取客户与角色列表
 *
 * @author WangZhiYao
 * @since 2022/6/30
 */
class GetCustomerWithCharacterListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) : IUseCase {

    operator fun invoke(): Flow<List<CustomerCharacterList>> =
        customerRepository.getCustomerWithCharacterList()
}