package com.yizhenwind.booster.component.lazy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.collection.ArrayMap
import com.yizhenwind.booster.component.base.IActivityArgs
import java.lang.reflect.Method
import kotlin.reflect.KClass

internal val activityArgsMethodSignature = arrayOf(Intent::class.java)
internal val activityArgsMethodMap = ArrayMap<KClass<out IActivityArgs>, Method>()

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
class ActivityArgsLazy<Args : IActivityArgs>(
    private val activityArgsClass: KClass<Args>,
    private val intentProducer: () -> Intent
) : Lazy<Args> {

    private var cached: Args? = null

    override val value: Args
        get() {
            var args = cached
            if (args == null) {
                val intent = intentProducer()
                val method: Method = activityArgsMethodMap[activityArgsClass]
                    ?: activityArgsClass.java.getMethod("deserialize", *activityArgsMethodSignature)
                        .also { method ->
                            // Save a reference to the method
                            activityArgsMethodMap[activityArgsClass] = method
                        }

                @SuppressLint("BanUncheckedReflection") // needed for method.invoke
                @Suppress("UNCHECKED_CAST")
                args = method.invoke(null, intent) as Args
                cached = args
            }
            return args
        }

    override fun isInitialized(): Boolean = cached != null
}