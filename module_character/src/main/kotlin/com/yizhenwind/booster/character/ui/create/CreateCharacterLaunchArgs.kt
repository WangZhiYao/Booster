package com.yizhenwind.booster.character.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IActivityLaunchArgDeserializer
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CreateCharacterLaunchArgs(
    val customer: Customer?,
    val openDetailAfterCreateSuccess: Boolean = false
) : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateCharacterActivity::class.java).apply {
            putExtra(IntentKey.CUSTOMER, customer)
            putExtra(IntentKey.OPEN_DETAIL_AFTER_CREATE_SUCCESS, openDetailAfterCreateSuccess)
        }

    companion object : IActivityLaunchArgDeserializer<CreateCharacterLaunchArgs> {

        override fun deserialize(intent: Intent): CreateCharacterLaunchArgs =
            CreateCharacterLaunchArgs(
                customer = intent.getParcelableExtra(IntentKey.CUSTOMER),
                openDetailAfterCreateSuccess = intent.getBooleanExtra(
                    IntentKey.OPEN_DETAIL_AFTER_CREATE_SUCCESS,
                    false
                )
            )

    }
}