package com.yizhenwind.booster.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 角色
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
@Parcelize
data class Character(
    val id: Long,
    val customerId: Long,
    val zone: Zone,
    val server: Server,
    val account: String,
    var password: String,
    var securityLock: String?,
    val name: String,
    val sect: Sect,
    val internal: Internal,
    var remark: String?,
    val createTime: Long
) : Parcelable