package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 创建用户 UseCase
 *
 * @author WangZhiYao
 * @since 2022/6/2
 */
class CreateCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(
        name: String,
        contactType: ContactType,
        contact: String,
        remark: String?
    ): Flow<Customer?> = customerRepository.createCustomer(name, contactType, contact, remark)

}