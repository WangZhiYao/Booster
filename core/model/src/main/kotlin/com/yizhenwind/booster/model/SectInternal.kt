package com.yizhenwind.booster.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * 门派心法
 *
 * @author WangZhiYao
 * @since 2022/4/26
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class SectInternal(
    val sect: Sect,
    val internalList: List<Internal>
) : Parcelable
