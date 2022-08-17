package com.yizhenwind.booster.component.ext

import android.os.Bundle
import com.yizhenwind.booster.component.base.IFragmentArgs
import com.yizhenwind.booster.component.delegate.FragmentArgsDelegate

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
inline fun <reified T : IFragmentArgs> fragmentArgs(noinline deserializer: (Bundle) -> T) =
    FragmentArgsDelegate(deserializer)