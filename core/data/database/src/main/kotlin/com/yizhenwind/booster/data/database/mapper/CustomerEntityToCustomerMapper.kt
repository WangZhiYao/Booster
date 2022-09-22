package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.entity.CustomerEntity
import com.yizhenwind.booster.model.Customer
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */
class CustomerEntityToCustomerMapper @Inject constructor() : IMapper<CustomerEntity, Customer> {

    override fun map(input: CustomerEntity) =
        input.run {
            Customer(
                id,
                name,
                contactType,
                contact,
                remark,
                createTime
            )
        }
}