package com.yizhenwind.booster.framework.base

import android.content.Context
import android.content.Intent

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface IActivityArgs {

    fun intent(context: Context): Intent

    fun launch(context: Context) = context.startActivity(intent(context))

}