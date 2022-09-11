package com.yizhenwind.booster.component.widget

import android.content.Context
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/4
 */
class ScrollTopSmoothScroller(context: Context) : LinearSmoothScroller(context) {

    override fun getHorizontalSnapPreference() = SNAP_TO_START

    override fun getVerticalSnapPreference() = SNAP_TO_START
}