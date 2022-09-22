package com.yizhenwind.booster.framework.ext

import android.content.Intent
import android.os.Build
import android.os.Parcelable

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/22
 */
inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}
