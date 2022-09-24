package com.yizhenwind.booster.customer.ui.tab

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.ui.tab.character.CustomerCharacterArgs
import com.yizhenwind.booster.customer.ui.tab.detail.CustomerDetailArgs
import com.yizhenwind.booster.customer.ui.tab.order.CustomerOrderArgs
import com.yizhenwind.booster.framework.base.BaseTabMVIActivity
import com.yizhenwind.booster.framework.ext.activityArgs
import com.yizhenwind.booster.framework.ext.setIntervalClickListener
import com.yizhenwind.booster.framework.ext.showSnack
import com.yizhenwind.booster.framework.widget.BoosterDialog
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.order.IOrderService
import com.yizhenwind.booster.model.Customer
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
class CustomerTabActivity :
    BaseTabMVIActivity<CustomerTabViewState, CustomerTabSideEffect>() {

    private val viewModel by viewModels<CustomerTabViewModel>()
    private val args by activityArgs<CustomerTabArgs>()

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    override fun initPage() {
        super.initPage()
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    fab.apply {
                        when (position) {
                            INDEX_DETAIL -> setImageResource(R.drawable.ic_round_edit_white_24dp)
                            else -> setImageResource(R.drawable.ic_round_add_white_24dp)
                        }
                    }
                }
            })

            fab.setIntervalClickListener {
                when (viewPager.currentItem) {
                    INDEX_DETAIL -> {
                        // TODO: Edit Customer Info
                    }
                    INDEX_CHARACTER -> {
                        characterService.launchCreateCharacter(
                            this@CustomerTabActivity,
                            viewModel.container.stateFlow.value.customer.id
                        )
                    }
                    INDEX_ORDER -> orderService.launchCreateOrder(
                        this@CustomerTabActivity,
                        args.customerId
                    )
                }
            }
        }
    }

    private fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
        viewModel.getCustomerById(args.customerId)
    }

    override fun getTabTitleList(): List<Int> =
        listOf(
            R.string.customer_info_detail,
            R.string.customer_info_character,
            R.string.customer_info_order
        )

    override fun getFragmentList(): List<Fragment> =
        args.run {
            listOf(
                CustomerDetailArgs().newInstance(),
                CustomerCharacterArgs(customerId).newInstance(),
                CustomerOrderArgs(customerId).newInstance()
            )
        }

    override fun render(state: CustomerTabViewState) {
        setPageTitle(state.customer.name)
    }

    override fun handleSideEffect(sideEffect: CustomerTabSideEffect) {
        when (sideEffect) {
            is CustomerTabSideEffect.GetCustomerFailure ->
                binding.root.showSnack(sideEffect.errorMessage)
            CustomerTabSideEffect.DeleteCustomerSuccess -> finish()
            is CustomerTabSideEffect.DeleteCustomerFailure ->
                binding.root.showSnack(sideEffect.errorMessage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_customer_info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            attemptDeleteCustomer(viewModel.container.stateFlow.value.customer)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun attemptDeleteCustomer(customer: Customer) {
        BoosterDialog.Builder()
            .setTitle(getString(R.string.dialog_customer_info_title))
            .setMessage(getString(R.string.dialog_customer_info_message_delete, customer.name))
            .setNegativeButton(getString(R.string.cancel)) { it.dismiss() }
            .setPositiveButton(getString(R.string.ok)) { viewModel.deleteCustomerById(customer.id) }
            .show(supportFragmentManager)
    }

    companion object {

        private const val INDEX_DETAIL = 0

        private const val INDEX_CHARACTER = 1

        private const val INDEX_ORDER = 2

    }

}