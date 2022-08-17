package com.yizhenwind.booster.component.base

import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
abstract class BaseTextInputActivity<out VB : ViewBinding>(
    bindingInflater: (LayoutInflater) -> VB
) : BaseActivity<VB>(bindingInflater) {

    protected fun showErrorInfo(
        textInputLayout: TextInputLayout,
        @StringRes errorMessage: Int,
        textInputEditText: TextInputEditText? = null
    ) {
        textInputLayout.error = getString(errorMessage)
        textInputEditText?.requestFocus()
    }
}