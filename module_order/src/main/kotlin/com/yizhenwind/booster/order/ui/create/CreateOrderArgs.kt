package com.yizhenwind.booster.order.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IActivityArgs
import com.yizhenwind.booster.component.base.IActivityArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2022/8/8
 */
data class CreateOrderArgs(
    val customerId: Long = Constant.DEFAULT_ID,
    val characterId: Long = Constant.DEFAULT_ID,
    val categoryId: Long = Constant.DEFAULT_ID,
    val subjectId: Long = Constant.DEFAULT_ID
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateOrderActivity::class.java).apply {
            putExtra(IntentKey.CUSTOMER_ID, customerId)
            putExtra(IntentKey.CHARACTER_ID, characterId)
            putExtra(IntentKey.CATEGORY_ID, categoryId)
            putExtra(IntentKey.SUBJECT_ID, subjectId)
        }

    companion object : IActivityArgsDeserializer<CreateOrderArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CreateOrderArgs = intent.run {
            CreateOrderArgs(
                getLongExtra(IntentKey.CUSTOMER_ID, Constant.DEFAULT_ID),
                getLongExtra(IntentKey.CHARACTER_ID, Constant.DEFAULT_ID),
                getLongExtra(IntentKey.CATEGORY_ID, Constant.DEFAULT_ID),
                getLongExtra(IntentKey.SUBJECT_ID, Constant.DEFAULT_ID)
            )
        }
    }
}