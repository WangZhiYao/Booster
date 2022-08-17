package com.yizhenwind.booster.data.network.response

import com.squareup.moshi.JsonClass
import com.yizhenwind.booster.common.model.SectInternalList

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@JsonClass(generateAdapter = true)
class SectInternalListResponse(
    override val code: Int,
    override val message: String,
    override val data: SectInternalList
) : BaseResponse<SectInternalList>()