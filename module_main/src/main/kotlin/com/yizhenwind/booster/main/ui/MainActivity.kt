package com.yizhenwind.booster.main.ui

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import com.yizhenwind.booster.component.base.BaseActivity
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.main.R
import com.yizhenwind.booster.main.databinding.ActivityMainBinding
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.mediator.order.IOrderService
import javax.inject.Inject

/**
 * 主界面
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var customerService: ICustomerService

    @Inject
    lateinit var orderService: IOrderService

    override fun init() {
        initView()
    }

    override fun showBack() = false

    private fun initView() {
        binding.apply {
            setSupportActionBar(appBarMain.toolbar)

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
            val navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.nav_customer_list, R.id.nav_category_list, R.id.nav_subject_list),
                drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            appBarMain.fab.setIntervalClickListener {
                when (navController.currentDestination?.id) {
                    R.id.nav_customer_list ->
                        customerService.launchCreateCustomer(this@MainActivity)
                    R.id.nav_category_list ->
                        orderService.launchCreateCategory(this@MainActivity)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}