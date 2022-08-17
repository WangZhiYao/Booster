package com.yizhenwind.booster.data.database.converter

import androidx.room.TypeConverter
import com.yizhenwind.booster.common.constant.PaymentStatus

/**
 * 付款状态
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
class PaymentStatusConverter : IConverter<PaymentStatus, String> {

    @TypeConverter
    override fun from(input: PaymentStatus): String = input.name

    @TypeConverter
    override fun to(output: String): PaymentStatus = enumValueOf(output)

}