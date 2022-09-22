package com.yizhenwind.booster.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.booster.data.database.entity.CharacterEntity
import com.yizhenwind.booster.data.database.entity.CustomerEntity
import com.yizhenwind.booster.data.database.entity.OrderEntity
import com.yizhenwind.booster.data.database.entity.SubjectEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/6/7
 */
data class OrderInfo(
    @Embedded
    val order: OrderEntity,

    @Relation(
        parentColumn = "customer_id",
        entityColumn = "id",
    )
    val customer: CustomerEntity,

    @Relation(
        parentColumn = "character_id",
        entityColumn = "id",
        entity = CharacterEntity::class
    )
    val character: CharacterInfo,

    @Relation(
        parentColumn = "subject_id",
        entityColumn = "id"
    )
    val subject: SubjectEntity
)