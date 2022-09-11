package com.yizhenwind.booster.component.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.ext.dp

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/9
 */
class TopSpaceItemDecoration(
    private val heightInDp: Float
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != 0) {
            outRect.top = heightInDp.dp.toInt()
        }
    }
}