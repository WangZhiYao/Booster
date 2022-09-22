package com.yizhenwind.booster.framework.ext

import android.os.SystemClock
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.booster.framework.R

/**
 *
 * @author WangZhiYao
 * @since 2021/11/29
 */
fun View.setIntervalClickListener(onClickListener: (View) -> Unit) {
    setOnClickListener(IntervalClickListener(onClickListener))
}

private class IntervalClickListener(
    private val onClickListener: (View) -> Unit,
    private var clickInterval: Int = 1000
) : View.OnClickListener {

    private var lastTimeClicked = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < clickInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onClickListener(v)
    }
}

fun View.showSnack(@StringRes resId: Int) {
    showSnack(resId, Snackbar.LENGTH_SHORT)
}

fun View.showSnack(text: String) {
    showSnack(text, Snackbar.LENGTH_SHORT)
}

fun View.showSnack(@StringRes resId: Int, duration: Int) {
    Snackbar.make(this, resId, duration).show()
}

fun View.showSnack(text: String, duration: Int) {
    Snackbar.make(this, text, duration).show()
}

fun View.showSnackWithAction(
    @StringRes resId: Int,
    @StringRes actionResId: Int,
    listener: View.OnClickListener
) {
    showSnackWithAction(resId, Snackbar.LENGTH_SHORT, actionResId, listener)
}

fun View.showSnackWithAction(
    @StringRes resId: Int,
    duration: Int,
    @StringRes actionResId: Int,
    listener: View.OnClickListener
) {
    Snackbar.make(this, resId, duration).setAction(actionResId, listener)
        .setActionTextColor(ContextCompat.getColor(context, R.color.color_secondary)).show()
}