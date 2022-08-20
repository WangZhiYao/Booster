package com.yizhenwind.booster.common.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
inline fun <T> List<T>.firstOrFirst(predicate: (T) -> Boolean): T? =
    this.firstOrNull(predicate) ?: this.firstOrNull()