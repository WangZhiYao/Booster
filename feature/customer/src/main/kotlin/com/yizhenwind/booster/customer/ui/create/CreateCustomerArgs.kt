package com.yizhenwind.booster.customer.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.framework.base.IActivityArgs
import com.yizhenwind.booster.framework.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class CreateCustomerArgs : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateCustomerActivity::class.java)

    companion object : IActivityArgsDeserializer<CreateCustomerArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CreateCustomerArgs =
            intent.run { CreateCustomerArgs() }

    }
}
