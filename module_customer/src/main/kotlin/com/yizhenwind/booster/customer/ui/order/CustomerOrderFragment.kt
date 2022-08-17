package com.yizhenwind.booster.customer.ui.order

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.customer.databinding.FragmentCustomerOrderBinding

/**
 *
 * @author WangZhiYao
 * @since 2022/6/2
 */
@AndroidEntryPoint
class CustomerOrderFragment :
    BaseFragment<FragmentCustomerOrderBinding>(FragmentCustomerOrderBinding::inflate) {

    override fun init() {

    }

    companion object {

        fun newInstance(customerId: Long) =
            CustomerOrderFragment().apply {
                arguments = Bundle().apply {
                    putLong(
                        IntentKey.CUSTOMER_ID,
                        customerId
                    )
                }
            }
    }
}