package com.yizhenwind.booster.common.model

import android.os.Parcelable
import com.yizhenwind.booster.common.constant.ContactType
import kotlinx.parcelize.Parcelize

/**
 * 客户
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */
@Parcelize
data class Customer(
    val id: Long,
    val name: String,
    var contactType: ContactType,
    var contact: String,
    var remark: String?,
    val createTime: Long
) : Parcelable