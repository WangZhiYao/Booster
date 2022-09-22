package com.yizhenwind.booster.framework.base

import android.os.Bundle

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface IFragmentArgDeserializer<T : IFragmentArgs> {

    fun deserialize(arguments: Bundle): T

}