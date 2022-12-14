package com.yizhenwind.booster.customer.ui.tab

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IActivityArgs
import com.yizhenwind.booster.component.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CustomerTabArgs(
    val customer: Customer,
    val tabIndex: Int = Constant.CustomerTab.INDEX_DETAIL
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CustomerTabActivity::class.java).apply {
            putExtra(IntentKey.CUSTOMER, customer)
            putExtra(IntentKey.TAB_INDEX, tabIndex)
        }

    companion object : IActivityArgsDeserializer<CustomerTabArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CustomerTabArgs = intent.run {
            CustomerTabArgs(
                requireNotNull(getParcelableExtra(IntentKey.CUSTOMER)) { IntentKey.CUSTOMER },
                getIntExtra(IntentKey.TAB_INDEX, Constant.CustomerTab.INDEX_DETAIL)
            )
        }
    }
}
