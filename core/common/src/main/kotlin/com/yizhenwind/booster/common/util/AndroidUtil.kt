package com.yizhenwind.booster.common.util

import android.content.Context
import com.yizhenwind.booster.logger.Logger

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/25
 */
object AndroidUtil {

    fun isPackageInstalled(appContext: Context, packageName: String): Boolean =
        try {
            val pm = appContext.packageManager
            pm.getPackageGids(packageName) != null
        } catch (ex: Exception) {
            Logger.e(ex)
            false
        }
}