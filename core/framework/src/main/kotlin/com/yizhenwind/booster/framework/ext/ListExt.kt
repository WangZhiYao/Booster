package com.yizhenwind.booster.framework.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
inline fun <T> List<T>.findFirstOrFirst(predicate: (T) -> Boolean): T? =
    this.firstOrNull(predicate) ?: this.firstOrNull()