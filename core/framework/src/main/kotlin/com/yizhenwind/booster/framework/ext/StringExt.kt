package com.yizhenwind.booster.framework.ext

/**
 *
 * @author WangZhiYao
 * @since 2022/7/21
 */
fun String?.emptyThenNull(): String? = if (this.isNullOrEmpty()) null else this

fun String?.blankThenNull(): String? = if (this.isNullOrBlank()) null else this