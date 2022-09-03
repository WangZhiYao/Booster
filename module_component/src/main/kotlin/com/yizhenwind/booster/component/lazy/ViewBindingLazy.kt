package com.yizhenwind.booster.component.lazy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.collection.ArrayMap
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method
import kotlin.reflect.KClass

internal val viewBindingMethodSignature = arrayOf(LayoutInflater::class.java)
internal val viewBindingMethodMap = ArrayMap<KClass<out ViewBinding>, Method>()

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
class ViewBindingLazy<VB : ViewBinding>(
    private val viewBindingClass: KClass<VB>,
    private val layoutInflaterProducer: () -> LayoutInflater
) : Lazy<VB> {

    private var cached: VB? = null

    override val value: VB
        get() {
            var args = cached
            if (args == null) {
                val layoutInflater = layoutInflaterProducer()
                val method: Method = viewBindingMethodMap[viewBindingClass]
                    ?: viewBindingClass.java.getMethod("inflate", *viewBindingMethodSignature)
                        .also { method ->
                            // Save a reference to the method
                            viewBindingMethodMap[viewBindingClass] = method
                        }

                @SuppressLint("BanUncheckedReflection") // needed for method.invoke
                @Suppress("UNCHECKED_CAST")
                args = method.invoke(null, layoutInflater) as VB
                cached = args
            }
            return args
        }

    override fun isInitialized(): Boolean = cached != null
}