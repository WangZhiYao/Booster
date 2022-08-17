package com.yizhenwind.booster.initializer

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

/**
 * Log 初始器
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
class LogInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.plant(BoosterDebugTree())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    private inner class BoosterDebugTree : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            super.log(
                priority,
                LOG_TAG,
                "${tag?.replace("$", "::")} | $message",
                t
            )
        }
    }

    companion object {

        private const val LOG_TAG = "Booster"
    }
}