package com.yizhenwind.booster.customer.ui.info

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.BaseActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.setupFragmentWithTab
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.widget.BoosterDialog
import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.databinding.ActivityCustomerInfoBinding
import com.yizhenwind.booster.customer.ui.info.character.CustomerCharacterArgs
import com.yizhenwind.booster.customer.ui.info.detail.CustomerDetailArgs
import com.yizhenwind.booster.customer.ui.info.order.CustomerOrderArgs
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 * 客户详情
 *
 * @author WangZhiYao
 * @since 2022/4/20
 */
@AndroidEntryPoint
class CustomerInfoActivity :
    BaseActivity<ActivityCustomerInfoBinding>(ActivityCustomerInfoBinding::inflate) {

    private val viewModel by viewModels<CustomerInfoViewModel>()

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    private val args by activityArgs(CustomerInfoLaunchArgs::deserialize)

    override fun init() {
        initData()
        initView()
    }

    override fun showBack() = true

    override fun setupHomeButton() {
        binding.apply {
            toolbar.setNavigationIcon(R.drawable.ic_round_arrow_white_24dp)
            setSupportActionBar(toolbar)
        }
        super.setupHomeButton()
    }

    private fun initView() {
        binding.apply {
            args.apply {
                collapsingToolbarLayout.title = customer.name
                vpCustomerInfo.apply {
                    setupFragmentWithTab(
                        this@CustomerInfoActivity,
                        tlCustomerInfo,
                        listOf(
                            R.string.customer_info_detail,
                            R.string.customer_info_character,
                            R.string.customer_info_order
                        ),
                        listOf(
                            CustomerDetailArgs(customer).newInstance(),
                            CustomerCharacterArgs(customer.id).newInstance(),
                            CustomerOrderArgs(customer.id).newInstance()
                        )
                    )
                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            fab.apply {
                                when (position) {
                                    INDEX_DETAIL -> setImageResource(R.drawable.ic_round_edit_white_24dp)
                                    else -> setImageResource(R.drawable.ic_round_add_white_24dp)
                                }
                            }
                        }
                    })
                }

                fab.setIntervalClickListener {
                    when (vpCustomerInfo.currentItem) {
                        INDEX_DETAIL -> {}
                        INDEX_CHARACTER -> characterService.launchCreateCharacter(
                            this@CustomerInfoActivity,
                            customer
                        )
                        INDEX_ORDER -> orderService.launchCreateOrder(
                            this@CustomerInfoActivity,
                            customer.id
                        )
                    }
                }
            }
        }
    }

    private fun initData() {
        viewModel.observe(this, sideEffect = ::handleSideEffect)
    }

    private fun handleSideEffect(sideEffect: CustomerInfoSideEffect) {
        when (sideEffect) {
            CustomerInfoSideEffect.DeleteCustomerSuccess -> finish()
            is CustomerInfoSideEffect.DeleteCustomerFailure ->
                binding.root.showSnack(sideEffect.errorMessage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_customer_info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            attemptDeleteCustomer(args.customer)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun attemptDeleteCustomer(customer: Customer) {
        BoosterDialog.Builder()
            .setTitle(getString(R.string.dialog_customer_info_title))
            .setMessage(getString(R.string.dialog_customer_info_message_delete, customer.name))
            .setNegativeButton(getString(R.string.cancel)) { it.dismiss() }
            .setPositiveButton(getString(R.string.ok)) { viewModel.deleteCustomer(customer) }
            .show(supportFragmentManager)
    }

    companion object {

        private const val INDEX_DETAIL = 0

        private const val INDEX_CHARACTER = 1

        private const val INDEX_ORDER = 2

    }
}