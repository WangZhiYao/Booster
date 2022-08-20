package com.yizhenwind.booster.common.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/19
 */
fun Long.formatToDateTime(pattern: String = "yyyy-MM-dd HH:mm"): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(Date(this))
}