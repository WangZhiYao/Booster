package com.yizhenwind.booster.data.network.response

import com.squareup.moshi.JsonClass
import com.yizhenwind.booster.model.ZoneServerList

/**
 *
 * @author WangZhiYao
 * @since 2022/6/4
 */
@JsonClass(generateAdapter = true)
class ZoneServerListResponse(
    override val code: Int,
    override val message: String,
    override val data: ZoneServerList
) : BaseResponse<ZoneServerList>()