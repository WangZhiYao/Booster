package com.yizhenwind.booster.common.model

import com.squareup.moshi.JsonClass

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@JsonClass(generateAdapter = true)
data class SectInternalList(
    val version: Int,
    val sectInternalList: List<SectInternal>
)