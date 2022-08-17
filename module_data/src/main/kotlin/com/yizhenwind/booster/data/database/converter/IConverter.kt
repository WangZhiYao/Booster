package com.yizhenwind.booster.data.database.converter

/**
 * Room 数据类型转换器
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
interface IConverter<I, O> {

    fun from(input: I): O

    fun to(output: O): I

}