package com.yizhenwind.booster.framework.widget

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * 封装对话框
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
class BoosterDialog private constructor(private val builder: Builder) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext())
            .setTitle(builder.title)
            .setMessage(builder.message)
            .setNegativeButton(builder.negativeButton) { _, _ ->
                builder.onNegativeClickListener?.invoke(this)
            }
            .setPositiveButton(builder.positiveButton) { _, _ ->
                builder.onPositiveClickListener?.invoke(this)
            }
            .create()

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    class Builder {
        var title: String? = null
            private set
        var message: String? = null
            private set

        var negativeButton: String? = null
            private set
        var onNegativeClickListener: ((BoosterDialog) -> Unit)? = null
            private set

        var positiveButton: String? = null
            private set
        var onPositiveClickListener: ((BoosterDialog) -> Unit)? = null
            private set

        fun setTitle(title: String) = apply { this.title = title }

        fun setMessage(message: String) = apply { this.message = message }

        fun setNegativeButton(
            negativeButton: String,
            onNegativeClickListener: ((BoosterDialog) -> Unit)
        ) =
            apply {
                this.negativeButton = negativeButton
                this.onNegativeClickListener = onNegativeClickListener
            }

        fun setPositiveButton(
            positiveButton: String,
            onPositiveClickListener: ((BoosterDialog) -> Unit)
        ) =
            apply {
                this.positiveButton = positiveButton
                this.onPositiveClickListener = onPositiveClickListener
            }

        fun build(): BoosterDialog = BoosterDialog(this)

        fun show(manager: FragmentManager): BoosterDialog =
            build().apply { show(manager) }
    }

    companion object {

        private const val TAG = "BoosterDialog"

    }
}