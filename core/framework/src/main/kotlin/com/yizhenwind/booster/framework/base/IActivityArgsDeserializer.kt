package com.yizhenwind.booster.framework.base

import android.content.Intent

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface IActivityArgsDeserializer<T : IActivityArgs> {

    fun deserialize(intent: Intent): T

}