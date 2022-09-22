package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.entity.CustomerEntity
import com.yizhenwind.booster.model.Customer
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class CustomerToCustomerEntityMapper @Inject constructor() : IMapper<Customer, CustomerEntity> {

    override fun map(input: Customer) =
        input.run {
            CustomerEntity(
                id,
                name,
                contactType,
                contact,
                remark,
                createTime
            )
        }
}