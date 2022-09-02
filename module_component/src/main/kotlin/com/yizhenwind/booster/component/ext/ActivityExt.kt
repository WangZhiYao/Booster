package com.yizhenwind.booster.component.ext

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/3/2
 */
inline fun <T : ViewBinding> Activity.viewBinding(
    crossinline inflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        inflater.invoke(layoutInflater)
    }

inline fun <reified T : IActivityLaunchArgs> Activity.activityArgs(
    crossinline deserializer: (Intent) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        deserializer(this.intent)
    }