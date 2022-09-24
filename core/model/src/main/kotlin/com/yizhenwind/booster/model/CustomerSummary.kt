package com.yizhenwind.booster.model

import com.yizhenwind.booster.common.constant.ContactType

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/24
 */
data class CustomerSummary(
    val id: Long,
    val name: String,
    val contactType: ContactType,
    val contact: String,
    val characterCount: Int,
    val orderCount: Int
)