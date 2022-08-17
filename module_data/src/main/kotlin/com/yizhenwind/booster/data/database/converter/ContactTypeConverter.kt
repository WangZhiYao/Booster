package com.yizhenwind.booster.data.database.converter

import androidx.room.TypeConverter
import com.yizhenwind.booster.common.constant.ContactType

/**
 * 联系方式转换器
 *
 * @author WangZhiYao
 * @since 2022/3/18
 */
class ContactTypeConverter : IConverter<ContactType, String> {

    @TypeConverter
    override fun from(input: ContactType): String = input.name

    @TypeConverter
    override fun to(output: String): ContactType = enumValueOf(output)

}