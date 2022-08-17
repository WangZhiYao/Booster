package com.yizhenwind.booster.common.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * 大区与服务器
 *
 * @author WangZhiYao
 * @since 2022/4/26
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class ZoneServer(
    val zone: Zone,
    val serverList: List<Server>
) : Parcelable
