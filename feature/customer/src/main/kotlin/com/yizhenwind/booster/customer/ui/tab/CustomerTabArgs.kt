package com.yizhenwind.booster.customer.ui.tab

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.framework.base.IActivityArgs
import com.yizhenwind.booster.framework.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CustomerTabArgs(
    val customerId: Long,
    val tabIndex: Int = Constant.CustomerTab.INDEX_DETAIL
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CustomerTabActivity::class.java).apply {
            putExtra(IntentKey.CUSTOMER_ID, customerId)
            putExtra(IntentKey.TAB_INDEX, tabIndex)
        }

    companion object : IActivityArgsDeserializer<CustomerTabArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CustomerTabArgs = intent.run {
            CustomerTabArgs(
                getLongExtra(IntentKey.CUSTOMER_ID, Constant.DEFAULT_ID),
                getIntExtra(IntentKey.TAB_INDEX, Constant.CustomerTab.INDEX_DETAIL)
            )
        }
    }
}
