package com.yizhenwind.booster.customer.ui.detail

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IActivityLaunchArgDeserializer
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CustomerDetailLaunchArgs(
    val customer: Customer
) : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CustomerDetailActivity::class.java).apply {
            putExtra(IntentKey.CUSTOMER, customer)
        }

    companion object : IActivityLaunchArgDeserializer<CustomerDetailLaunchArgs> {

        override fun deserialize(intent: Intent): CustomerDetailLaunchArgs =
            CustomerDetailLaunchArgs(
                requireNotNull(
                    intent.getParcelableExtra(IntentKey.CUSTOMER)
                ) {
                    IntentKey.CUSTOMER
                }
            )

    }
}
