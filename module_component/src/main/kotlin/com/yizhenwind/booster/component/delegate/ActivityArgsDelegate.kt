package com.yizhenwind.booster.component.delegate

import android.app.Activity
import android.content.Intent
import com.yizhenwind.booster.component.base.IActivityLaunchArgs
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class ActivityArgsDelegate<T : IActivityLaunchArgs>(
    private val deserializer: (Intent) -> T
) : ReadOnlyProperty<Activity, T> {

    override fun getValue(thisRef: Activity, property: KProperty<*>): T =
        deserializer(thisRef.intent)

}