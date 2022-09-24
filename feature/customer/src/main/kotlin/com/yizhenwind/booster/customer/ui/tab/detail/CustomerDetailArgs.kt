package com.yizhenwind.booster.customer.ui.tab.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.framework.base.IFragmentArgDeserializer
import com.yizhenwind.booster.framework.base.IFragmentArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/20
 */
class CustomerDetailArgs : IFragmentArgs {

    override fun newInstance(): Fragment = CustomerDetailFragment()

    companion object : IFragmentArgDeserializer<CustomerDetailArgs> {

        @JvmStatic
        override fun deserialize(arguments: Bundle): CustomerDetailArgs = arguments.run {
            CustomerDetailArgs()
        }
    }
}