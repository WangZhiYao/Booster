package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.data.database.entity.CustomerEntity
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */
class CustomerEntityToCustomerMapper @Inject constructor() : IMapper<CustomerEntity, Customer> {

    override fun invoke(input: CustomerEntity) =
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