package com.yizhenwind.booster.customer.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
class CreateCustomerLaunchArgs : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateCustomerActivity::class.java)

}
