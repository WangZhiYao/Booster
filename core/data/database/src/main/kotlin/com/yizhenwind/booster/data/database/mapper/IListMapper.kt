package com.yizhenwind.booster.data.database.mapper

/**
 *
 * @author WangZhiYao
 * @since 2022/3/9
 */
interface IListMapper<in I, out O> : IMapper<List<I>, List<O>>

class ListMapper<in I, out O>(private val mapper: IMapper<I, O>) : IListMapper<I, O> {

    override fun map(input: List<I>): List<O> = input.map { mapper.map(it) }

}