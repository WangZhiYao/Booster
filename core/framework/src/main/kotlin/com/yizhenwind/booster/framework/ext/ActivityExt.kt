package com.yizhenwind.booster.framework.ext

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.yizhenwind.booster.framework.base.ActivityArgsLazy
import com.yizhenwind.booster.framework.base.IActivityArgs
import com.yizhenwind.booster.framework.base.ViewBindingLazy

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/3/2
 */
inline fun <reified VB : ViewBinding> Activity.viewBindings(): ViewBindingLazy<VB> =
    ViewBindingLazy(VB::class) { layoutInflater }

inline fun <reified Args : IActivityArgs> Activity.activityArgs(): ActivityArgsLazy<Args> =
    ActivityArgsLazy(Args::class) {
        intent ?: throw IllegalStateException("Activity $this has a null Intent")
    }