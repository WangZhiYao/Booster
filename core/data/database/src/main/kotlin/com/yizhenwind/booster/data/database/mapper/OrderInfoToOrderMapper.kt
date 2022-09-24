package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.data.database.dto.OrderInfoDto
import com.yizhenwind.booster.model.Order
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/7
 */
class OrderInfoToOrderMapper @Inject constructor(
    private val customerEntityToCustomerMapper: CustomerEntityToCustomerMapper,
    private val characterInfoToCharacterMapper: CharacterInfoToCharacterMapper,
    private val subjectEntityToSubjectMapper: SubjectEntityToSubjectMapper,
) : IMapper<OrderInfoDto, Order> {

    override fun map(input: OrderInfoDto) =
        input.run {
            Order(
                order.id,
                customerEntityToCustomerMapper.map(customer),
                characterInfoToCharacterMapper.map(character),
                subjectEntityToSubjectMapper.map(subject),
                order.billingCycle,
                order.originalPrice,
                order.startDate,
                order.endDate,
                order.remark,
                order.status,
                order.statusUpdateTime,
                order.paymentStatus,
                order.paymentTime,
                order.refundAmount,
                order.refundTime,
                order.updateTime,
                order.createTime
            )
        }
}