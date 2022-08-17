package com.yizhenwind.booster.component.base

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