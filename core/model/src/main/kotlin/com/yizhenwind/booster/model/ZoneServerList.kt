package com.yizhenwind.booster.model

import com.squareup.moshi.JsonClass

/**
 *
 * @author WangZhiYao
 * @since 2022/6/4
 */
@JsonClass(generateAdapter = true)
data class ZoneServerList(
    val version: Int,
    val zoneServerList: List<ZoneServer>
)