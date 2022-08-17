package com.yizhenwind.booster.data.database.converter

import androidx.room.TypeConverter
import com.yizhenwind.booster.common.constant.BillingCycle

/**
 * 结算周期转换器
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
class BillingCycleConverter : IConverter<BillingCycle, String> {

    @TypeConverter
    override fun from(input: BillingCycle): String = input.name

    @TypeConverter
    override fun to(output: String): BillingCycle = enumValueOf(output)

}