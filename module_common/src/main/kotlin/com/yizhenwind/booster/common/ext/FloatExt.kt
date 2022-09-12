package com.yizhenwind.booster.common.ext

import android.content.res.Resources
import android.util.TypedValue

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )