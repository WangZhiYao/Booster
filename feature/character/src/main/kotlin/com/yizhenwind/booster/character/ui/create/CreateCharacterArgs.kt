package com.yizhenwind.booster.character.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.framework.base.IActivityArgs
import com.yizhenwind.booster.framework.base.IActivityArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CreateCharacterArgs(
    val customerId: Long,
    val openDetailAfterCreateSuccess: Boolean = false
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateCharacterActivity::class.java).apply {
            putExtra(IntentKey.CUSTOMER_ID, customerId)
            putExtra(IntentKey.OPEN_DETAIL_AFTER_CREATE_SUCCESS, openDetailAfterCreateSuccess)
        }

    companion object : IActivityArgsDeserializer<CreateCharacterArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CreateCharacterArgs = intent.run {
            CreateCharacterArgs(
                customerId = getLongExtra(IntentKey.CUSTOMER_ID, Constant.DEFAULT_ID),
                openDetailAfterCreateSuccess = getBooleanExtra(
                    IntentKey.OPEN_DETAIL_AFTER_CREATE_SUCCESS,
                    false
                )
            )
        }

    }
}