package com.yizhenwind.booster.customer.ui.tab.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IFragmentArgDeserializer
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CustomerCharacterArgs(
    val customerId: Long
) : IFragmentArgs {

    override fun newInstance(): Fragment = CustomerCharacterFragment().apply {
        arguments = Bundle().apply {
            putLong(IntentKey.CUSTOMER_ID, customerId)
        }
    }

    companion object : IFragmentArgDeserializer<CustomerCharacterArgs> {

        override fun deserialize(arguments: Bundle): CustomerCharacterArgs =
            CustomerCharacterArgs(arguments.getLong(IntentKey.CUSTOMER_ID))

    }
}