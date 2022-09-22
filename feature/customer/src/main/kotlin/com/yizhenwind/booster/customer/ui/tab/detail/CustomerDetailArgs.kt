package com.yizhenwind.booster.customer.ui.tab.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.framework.base.IFragmentArgDeserializer
import com.yizhenwind.booster.framework.base.IFragmentArgs
import com.yizhenwind.booster.model.Customer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/20
 */
data class CustomerDetailArgs(
    val customer: Customer
) : IFragmentArgs {

    override fun newInstance(): Fragment = CustomerDetailFragment().apply {
        arguments = Bundle().apply {
            putParcelable(IntentKey.CUSTOMER, customer)
        }
    }

    companion object : IFragmentArgDeserializer<CustomerDetailArgs> {

        @JvmStatic
        override fun deserialize(arguments: Bundle): CustomerDetailArgs = arguments.run {
            CustomerDetailArgs(requireNotNull(getParcelable(IntentKey.CUSTOMER)) { IntentKey.CUSTOMER })
        }
    }
}