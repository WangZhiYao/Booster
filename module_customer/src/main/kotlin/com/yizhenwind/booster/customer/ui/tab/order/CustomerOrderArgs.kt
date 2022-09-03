package com.yizhenwind.booster.customer.ui.tab.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IFragmentArgDeserializer
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
data class CustomerOrderArgs(
    val customerId: Long
) : IFragmentArgs {

    override fun newInstance(): Fragment = CustomerOrderFragment().apply {
        arguments = Bundle().apply {
            putLong(IntentKey.CUSTOMER_ID, customerId)
        }
    }

    companion object : IFragmentArgDeserializer<CustomerOrderArgs> {

        @JvmStatic
        override fun deserialize(arguments: Bundle): CustomerOrderArgs = arguments.run {
            CustomerOrderArgs(getLong(IntentKey.CUSTOMER_ID))
        }
    }

}