package com.yizhenwind.booster.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
@Parcelize
data class Subject(
    val id: Long,
    val categoryId: Long,
    val content: String,
    val createTime: Long
) : Parcelable
