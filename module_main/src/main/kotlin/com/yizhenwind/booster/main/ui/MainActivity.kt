package com.yizhenwind.booster.main.ui

import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.booster.component.base.BaseActivity
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.viewBinding
import com.yizhenwind.booster.main.R
import com.yizhenwind.booster.main.databinding.ActivityMainBinding
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

    private val binding by viewBinding(ActivityMainBinding::inflate)

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
            setSupportActionBar(appBarMain.toolbar)

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
            val navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.nav_home, R.id.nav_customer_list, R.id.nav_order_list),
                drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            appBarMain.fab.setIntervalClickListener {
                when (navController.currentDestination?.id) {
                    R.id.nav_customer_list ->
                        customerService.launchCreateCustomer(this@MainActivity)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}