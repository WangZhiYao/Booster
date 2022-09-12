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
import com.yizhenwind.booster.component.databinding.LayoutTextLabelViewBinding
import com.yizhenwind.booster.component.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class TextLabelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = viewBinding(LayoutTextLabelViewBinding::inflate, attachToParent = true)

    var title: CharSequence?
        get() = binding.tvTextLabelTitle.text
        set(value) {
            binding.tvTextLabelTitle.text = value
        }

    var titleTextSize: Float
        get() = binding.tvTextLabelTitle.textSize
        set(value) {
            binding.tvTextLabelTitle.textSize = value
        }

    var titleMaxLines: Int
        get() = binding.tvTextLabelTitle.maxLines
        set(value) {
            binding.tvTextLabelTitle.maxLines = value
        }

    var titleMinLines: Int
        get() = binding.tvTextLabelTitle.minLines
        set(value) {
            binding.tvTextLabelTitle.minLines = value
        }

    var titleEllipsize: TextUtils.TruncateAt?
        get() = binding.tvTextLabelTitle.ellipsize
        set(value) {
            binding.tvTextLabelTitle.ellipsize = value
        }

    var content: CharSequence?
        get() = binding.tvTextLabelContent.text
        set(value) {
            binding.tvTextLabelContent.text = value
        }

    var contentTextSize: Float
        get() = binding.tvTextLabelContent.textSize
        set(value) {
            binding.tvTextLabelContent.textSize = value
        }

    var contentMaxLines: Int
        get() = binding.tvTextLabelContent.maxLines
        set(value) {
            binding.tvTextLabelContent.maxLines = value
        }

    var contentMinLines: Int
        get() = binding.tvTextLabelContent.minLines
        set(value) {
            binding.tvTextLabelContent.minLines = value
        }

    var contentEllipsize: TextUtils.TruncateAt?
        get() = binding.tvTextLabelContent.ellipsize
        set(value) {
            binding.tvTextLabelContent.ellipsize = value
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.TextLabelView).use { ta ->
            ta.apply {
                binding.apply {
                    tvTextLabelTitle.apply {
                        text = getString(R.styleable.TextLabelView_title)
                        setTextSize(
                            TypedValue.COMPLEX_UNIT_PX,
                            getDimensionPixelSize(
                                R.styleable.TextLabelView_titleTextSize,
                                18f.sp.toInt()
                            ).toFloat()
                        )
                        setTextColor(
                            getColor(
                                R.styleable.TextLabelView_titleTextColor,
                                ContextCompat.getColor(context, R.color.color_text_secondary)
                            )
                        )
                        maxLines =
                            getInteger(R.styleable.TextLabelView_titleMaxLines, Int.MAX_VALUE)
                        minLines = getInteger(R.styleable.TextLabelView_titleMinLines, 0)
                        ellipsize =
                            getEllipsize(getInt(R.styleable.TextLabelView_titleEllipsize, 0))
                    }

                    tvTextLabelContent.apply {
                        text = getString(R.styleable.TextLabelView_content)
                        setTextSize(
                            TypedValue.COMPLEX_UNIT_PX, getDimensionPixelSize(
                                R.styleable.TextLabelView_contentTextSize,
                                14f.sp.toInt()
                            ).toFloat()
                        )
                        setTextColor(
                            getColor(
                                R.styleable.TextLabelView_contentTextColor,
                                ContextCompat.getColor(context, R.color.color_text_primary)
                            )
                        )
                        maxLines =
                            getInteger(R.styleable.TextLabelView_contentMaxLines, Int.MAX_VALUE)
                        minLines = getInteger(R.styleable.TextLabelView_contentMinLines, 0)
                        ellipsize =
                            getEllipsize(getInt(R.styleable.TextLabelView_contentEllipsize, 0))
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