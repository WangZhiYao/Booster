package com.yizhenwind.booster.data.database.dto

import androidx.room.ColumnInfo
import com.yizhenwind.booster.common.constant.ContactType

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/24
 */
data class CustomerSummaryDto(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "contact_type")
    val contactType: ContactType,
    val contact: String,
    @ColumnInfo(name = "character_count")
    val characterCount: Int,
    @ColumnInfo(name = "order_count")
    val orderCount: Int
)