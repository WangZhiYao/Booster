package com.yizhenwind.booster.data.network.response

/**
 *
 * @author WangZhiYao
 * @since 2022/6/4
 */
abstract class BaseResponse<T> {
    abstract val code: Int
    abstract val message: String
    abstract val data: T
}

