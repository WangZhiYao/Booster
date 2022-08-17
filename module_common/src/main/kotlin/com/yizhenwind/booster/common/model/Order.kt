package com.yizhenwind.booster.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.common.constant.OrderStatus
import com.yizhenwind.booster.common.constant.PaymentStatus

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
@Parcelize
data class Order(
    val id: Long,
    val customer: Customer,
    val character: Character,
    val subject: Subject,
    val billingCycle: BillingCycle,
    val originalPrice: Long,
    var startDate: Long,
    var endData: Long? = null,
    var remark: String? = null,
    var status: OrderStatus,
    var statusUpdateTime: Long,
    var paymentStatus: PaymentStatus,
    var paymentTime: Long? = null,
    var refundAmount: Long? = null,
    var refundTime: Long? = null,
    var updateTime: Long,
    val createTime: Long
) : Parcelable