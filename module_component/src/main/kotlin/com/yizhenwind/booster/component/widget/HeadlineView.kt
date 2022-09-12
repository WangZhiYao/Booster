package com.yizhenwind.booster.component.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import com.yizhenwind.booster.common.ext.sp
import com.yizhenwind.booster.component.R
import com.yizhenwind.booster.component.databinding.LayoutHeadlineViewBinding
import com.yizhenwind.booster.component.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class HeadlineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = viewBinding(LayoutHeadlineViewBinding::inflate, attachToParent = true)

    var title: CharSequence?
        get() = binding.tvHeadline.text
        set(value) {
            binding.tvHeadline.text = value
        }

    var titleTextSize: Float
        get() = binding.tvHeadline.textSize
        set(value) {
            binding.tvHeadline.textSize = value
        }

    var titleMaxLines: Int
        get() = binding.tvHeadline.maxLines
        set(value) {
            binding.tvHeadline.maxLines = value
        }

    var titleMinLines: Int
        get() = binding.tvHeadline.minLines
        set(value) {
            binding.tvHeadline.minLines = value
        }

    var titleEllipsize: TextUtils.TruncateAt?
        get() = binding.tvHeadline.ellipsize
        set(value) {
            binding.tvHeadline.ellipsize = value
        }

    var content: CharSequence?
        get() = binding.tvDetail.text
        set(value) {
            binding.tvDetail.text = value
        }

    var contentTextSize: Float
        get() = binding.tvDetail.textSize
        set(value) {
            binding.tvDetail.textSize = value
        }

    var contentMaxLines: Int
        get() = binding.tvDetail.maxLines
        set(value) {
            binding.tvDetail.maxLines = value
        }

    var contentMinLines: Int
        get() = binding.tvDetail.minLines
        set(value) {
            binding.tvDetail.minLines = value
        }

    var contentEllipsize: TextUtils.TruncateAt?
        get() = binding.tvDetail.ellipsize
        set(value) {
            binding.tvDetail.ellipsize = value
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.HeadlineView).use { ta ->
            ta.apply {
                binding.apply {
                    tvHeadline.apply {
                        text = getString(R.styleable.HeadlineView_headline)
                        setTextSize(
                            TypedValue.COMPLEX_UNIT_PX,
                            getDimensionPixelSize(
                                R.styleable.HeadlineView_headlineTextSize,
                                18f.sp.toInt()
                            ).toFloat()
                        )
                        setTextColor(
                            getColor(
                                R.styleable.HeadlineView_headlineTextColor,
                                ContextCompat.getColor(context, R.color.color_text_secondary)
                            )
                        )
                        maxLines =
                            getInteger(R.styleable.HeadlineView_headlineMaxLines, Int.MAX_VALUE)
                        minLines = getInteger(R.styleable.HeadlineView_headlineMinLines, 0)
                        ellipsize =
                            getEllipsize(getInt(R.styleable.HeadlineView_headlineEllipsize, 0))
                    }

                    tvDetail.apply {
                        text = getString(R.styleable.HeadlineView_detail)
                        setTextSize(
                            TypedValue.COMPLEX_UNIT_PX, getDimensionPixelSize(
                                R.styleable.HeadlineView_detailTextSize,
                                14f.sp.toInt()
                            ).toFloat()
                        )
                        setTextColor(
                            getColor(
                                R.styleable.HeadlineView_detailTextColor,
                                ContextCompat.getColor(context, R.color.color_text_primary)
                            )
                        )
                        maxLines =
                            getInteger(R.styleable.HeadlineView_detailMaxLines, Int.MAX_VALUE)
                        minLines = getInteger(R.styleable.HeadlineView_detailMinLines, 0)
                        ellipsize =
                            getEllipsize(getInt(R.styleable.HeadlineView_detailEllipsize, 0))
                    }
                }
            }
        }
    }

    private fun getEllipsize(value: Int) = when (value) {
        0 -> null
        1 -> TextUtils.TruncateAt.START
        2 -> TextUtils.TruncateAt.MIDDLE
        3 -> TextUtils.TruncateAt.END
        4 -> TextUtils.TruncateAt.MARQUEE
        else -> null
    }
}