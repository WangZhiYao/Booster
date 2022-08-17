package com.yizhenwind.booster.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.OrderStatus
import com.yizhenwind.booster.common.constant.PaymentStatus

/**
 * 订单属性
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Entity(
    tableName = "order",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customer_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["character_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class OrderEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = Constant.DEFAULT_ID,

    /**
     * 客户ID
     */
    @ColumnInfo(name = "customer_id", index = true)
    val customerId: Long,

    /**
     * 代练角色
     */
    @ColumnInfo(name = "character_id", index = true)
    val characterId: Long,

    /**
     * 代练项目
     */
    @ColumnInfo(name = "subject_id", index = true)
    val subjectId: Long,

    /**
     * 收费模式
     */
    @ColumnInfo(name = "billing_cycle")
    val billingCycle: BillingCycle,

    /**
     * 原价（分）
     */
    @ColumnInfo(name = "original_price")
    val originalPrice: Long,

    /**
     * 开始时间
     */
    @ColumnInfo(name = "start_date")
    var startDate: Long,

    /**
     * 结束时间
     */
    @ColumnInfo(name = "end_date")
    var endDate: Long? = null,

    /**
     * 备注
     */
    var remark: String? = null,

    /**
     * 订单状态
     */
    var status: OrderStatus,

    /**
     * 订单状态更新时间
     */
    @ColumnInfo(name = "status_update_time")
    var statusUpdateTime: Long,

    /**
     * 付款状态
     */
    @ColumnInfo(name = "payment_status")
    var paymentStatus: PaymentStatus,

    /**
     * 付款时间
     */
    @ColumnInfo(name = "payment_time")
    var paymentTime: Long? = null,

    /**
     * 退款金额
     */
    @ColumnInfo(name = "refund_amount")
    var refundAmount: Long? = null,

    /**
     * 退款时间
     */
    @ColumnInfo(name = "refund_time")
    var refundTime: Long? = null,

    /**
     * 更新时间
     */
    @ColumnInfo(name = "update_time")
    var updateTime: Long,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)
