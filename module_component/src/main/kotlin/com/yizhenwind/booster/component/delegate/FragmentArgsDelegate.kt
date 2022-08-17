package com.yizhenwind.booster.component.delegate

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.component.base.IFragmentArgs
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class FragmentArgsDelegate<T : IFragmentArgs>(
    private val deserializer: (Bundle) -> T
) : ReadOnlyProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        deserializer(thisRef.requireArguments())

}