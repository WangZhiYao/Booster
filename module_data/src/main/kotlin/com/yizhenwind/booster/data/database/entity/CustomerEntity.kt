package com.yizhenwind.booster.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.ContactType

/**
 * 客户属性
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Entity(
    tableName = "customer",
    indices = [
        Index(value = ["contact_type", "contact"], unique = true)
    ]
)
data class CustomerEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = Constant.DEFAULT_ID,

    /**
     * 客户名
     */
    val name: String,

    /**
     * 联系方式类型
     */
    @ColumnInfo(name = "contact_type")
    var contactType: ContactType,

    /**
     * 联系方式
     */
    var contact: String,

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