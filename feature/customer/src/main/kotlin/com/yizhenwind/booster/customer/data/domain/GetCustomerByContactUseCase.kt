package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.Customer
import javax.inject.Inject

/**
 * 根据联系方式查找客户
 *
 * @author WangZhiYao
 * @since 2022/6/2
 */
class GetCustomerByContactUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    suspend operator fun invoke(contactType: ContactType, contact: String): Customer? =
        customerRepository.getCustomerByContact(contactType, contact)
}