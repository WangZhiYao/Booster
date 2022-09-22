package com.yizhenwind.booster.home.ui

import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.booster.framework.base.BaseActivity
import com.yizhenwind.booster.framework.ext.setIntervalClickListener
import com.yizhenwind.booster.framework.ext.viewBindings
import com.yizhenwind.booster.home.R
import com.yizhenwind.booster.home.databinding.ActivityMainBinding
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 主界面
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBindings<ActivityMainBinding>()

    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var customerService: ICustomerService

    @Inject
    lateinit var orderService: IOrderService

    override fun getRootView(): View = binding.root

    override fun initPage() {
        super.initPage()
        initView()
    }

    private fun initView() {
        binding.apply {
            appBar.apply {
                setSupportActionBar(toolbar)
                val navController =
                    contentMain.navHostFragment.getFragment<NavHostFragment>().navController
                appBarConfiguration = AppBarConfiguration(
                    setOf(R.id.nav_home, R.id.nav_customer_list, R.id.nav_order_list),
                    drawerLayout
                )
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)
                fab.setIntervalClickListener {
                    when (navController.currentDestination?.id) {
                        R.id.nav_customer_list ->
                            customerService.launchCreateCustomer(this@MainActivity)
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}