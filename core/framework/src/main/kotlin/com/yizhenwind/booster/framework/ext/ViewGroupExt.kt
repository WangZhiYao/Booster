package com.yizhenwind.booster.framework.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

/**
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
inline fun <T : ViewBinding> ViewGroup.viewBinding(
    crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = false
) = inflater.invoke(LayoutInflater.from(context), this, attachToParent)

fun ViewGroup.inflate(@LayoutRes resourceId: Int, attachToParent: Boolean = false): View =
    LayoutInflater.from(context).inflate(resourceId, this, attachToParent)