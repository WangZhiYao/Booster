package com.yizhenwind.booster.logger.initializer

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/16
 */
class LoggerInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.plant(BoosterDebugTree())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    private inner class BoosterDebugTree : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            super.log(priority, "$LOG_TAG - $tag", message, t)
        }
    }

    companion object {

        private const val LOG_TAG = "Booster"
    }
}