package com.yizhenwind.booster.data.database.converter

import androidx.room.TypeConverter
import com.yizhenwind.booster.common.constant.OrderStatus

/**
 * 订单状态转换器
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
class OrderStatusConverter : IConverter<OrderStatus, String> {

    @TypeConverter
    override fun from(input: OrderStatus): String = input.name

    @TypeConverter
    override fun to(output: String): OrderStatus = enumValueOf(output)

}