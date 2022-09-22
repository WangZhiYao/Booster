package com.yizhenwind.booster.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.yizhenwind.booster.common.constant.Constant

/**
 * 账号属性
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Entity(
    tableName = "character",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customer_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class CharacterEntity(

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
     * 大区
     */
    @ColumnInfo(name = "zone_id")
    val zoneId: Long,

    /**
     * 服务器
     */
    @ColumnInfo(name = "server_id")
    val serverId: Long,

    /**
     * 账号
     */
    val account: String,

    /**
     * 密码
     */
    var password: String,

    /**
     * 仓库锁
     */
    @ColumnInfo(name = "security_lock")
    var securityLock: String? = null,

    /**
     * 角色名
     */
    val name: String,

    /**
     * 门派
     */
    @ColumnInfo(name = "sect_id")
    val sectId: Long,

    /**
     * 心法
     */
    @ColumnInfo(name = "internal_id")
    val internalId: Long,

    /**
     * 备注
     */
    var remark: String? = null,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)