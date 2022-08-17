package com.yizhenwind.booster.component.ext

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.yizhenwind.booster.component.delegate.ActivityArgsDelegate
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/3/2
 */
inline fun <T : ViewBinding> Activity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

inline fun <reified T : Any> Activity.extra(key: String, defaultValue: T? = null) = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else defaultValue
}

inline fun <reified T : IActivityLaunchArgs> activityArgs(noinline deserializer: (Intent) -> T) =
    ActivityArgsDelegate(deserializer)