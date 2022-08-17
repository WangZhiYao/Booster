package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.data.database.model.OrderInfo
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
) : IMapper<OrderInfo, Order> {

    override fun map(input: OrderInfo) =
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