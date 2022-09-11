package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.data.database.entity.CustomerEntity
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class CustomerToCustomerEntityMapper @Inject constructor() : IMapper<Customer, CustomerEntity> {

    override fun invoke(input: Customer) =
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