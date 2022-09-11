package com.yizhenwind.booster.common.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Sect(
    val id: Long,
    val name: String,
    val iconUrl: String
) : Parcelable
