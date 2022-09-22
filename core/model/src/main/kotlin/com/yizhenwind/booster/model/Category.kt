package com.yizhenwind.booster.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
@Parcelize
data class Category(
    val id: Long,
    val title: String,
    var description: String?,
    val createTime: Long
) : Parcelable
