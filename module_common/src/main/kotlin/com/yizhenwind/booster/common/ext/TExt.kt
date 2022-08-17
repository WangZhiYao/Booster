package com.yizhenwind.booster.common.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/6/30
 */
inline fun <T, R> T?.ifNullOrElse(ifNull: () -> R, `else`: (T) -> R) =
    if (this == null) ifNull() else `else`(this)

