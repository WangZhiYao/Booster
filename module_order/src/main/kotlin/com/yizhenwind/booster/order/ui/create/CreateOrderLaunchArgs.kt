package com.yizhenwind.booster.order.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IActivityLaunchArgDeserializer
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 * @author WangZhiYao
 * @since 2022/8/8
 */
data class CreateOrderLaunchArgs(
    val customerId: Long = Constant.DEFAULT_ID,
    val characterId: Long = Constant.DEFAULT_ID,
    val categoryId: Long = Constant.DEFAULT_ID,
    val subjectId: Long = Constant.DEFAULT_ID
) : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateOrderActivity::class.java).apply {
            putExtra(IntentKey.CUSTOMER_ID, customerId)
            putExtra(IntentKey.CHARACTER_ID, characterId)
            putExtra(IntentKey.CATEGORY_ID, categoryId)
            putExtra(IntentKey.SUBJECT_ID, subjectId)
        }

    companion object : IActivityLaunchArgDeserializer<CreateOrderLaunchArgs> {

        override fun deserialize(intent: Intent): CreateOrderLaunchArgs = intent.run {
            CreateOrderLaunchArgs(
                getLongExtra(IntentKey.CUSTOMER_ID, Constant.DEFAULT_ID),
                getLongExtra(IntentKey.CHARACTER_ID, Constant.DEFAULT_ID),
                getLongExtra(IntentKey.CATEGORY_ID, Constant.DEFAULT_ID),
                getLongExtra(IntentKey.SUBJECT_ID, Constant.DEFAULT_ID)
            )
        }
    }
}