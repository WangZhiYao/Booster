package com.yizhenwind.booster.component.base

import android.content.Intent

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface IActivityLaunchArgDeserializer<T : IActivityLaunchArgs> {

    fun deserialize(intent: Intent): T

}