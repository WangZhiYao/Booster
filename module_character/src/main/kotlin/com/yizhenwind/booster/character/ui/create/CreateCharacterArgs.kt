package com.yizhenwind.booster.character.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IActivityArgs
import com.yizhenwind.booster.component.base.IActivityArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CreateCharacterArgs(
    val customer: Customer?,
    val openDetailAfterCreateSuccess: Boolean = false
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateCharacterActivity2::class.java).apply {
            putExtra(IntentKey.CUSTOMER, customer)
            putExtra(IntentKey.OPEN_DETAIL_AFTER_CREATE_SUCCESS, openDetailAfterCreateSuccess)
        }

    companion object : IActivityArgsDeserializer<CreateCharacterArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CreateCharacterArgs = intent.run {
            CreateCharacterArgs(
                customer = getParcelableExtra(IntentKey.CUSTOMER),
                openDetailAfterCreateSuccess = getBooleanExtra(
                    IntentKey.OPEN_DETAIL_AFTER_CREATE_SUCCESS,
                    false
                )
            )
        }

    }
}