package com.yizhenwind.booster.customer.ui.detail

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.BaseActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.widget.BoosterDialog
import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.databinding.ActivityCustomerDetailBinding
import com.yizhenwind.booster.customer.ui.character.CustomerCharacterArgs
import com.yizhenwind.booster.customer.ui.order.CustomerOrderArgs
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.order.IOrderService
import javax.inject.Inject

/**
 * 客户详情
 *
 * @author WangZhiYao
 * @since 2022/4/20
 */
@AndroidEntryPoint
class CustomerDetailActivity :
    BaseActivity<ActivityCustomerDetailBinding>(ActivityCustomerDetailBinding::inflate) {

    private val viewModel by viewModels<CustomerDetailViewModel>()

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    private val args by activityArgs(CustomerDetailLaunchArgs::deserialize)

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

                vpCustomerDetail.apply {
                    adapter = CustomerDetailPageAdapter(
                        this@CustomerDetailActivity,
                        arrayListOf(
                            CustomerCharacterArgs(customer.id).newInstance(),
                            CustomerOrderArgs(customer.id).newInstance()
                        )
                    )
                    (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                }

                TabLayoutMediator(tlCustomerDetail, vpCustomerDetail) { tab, position ->
                    tab.setText(TAB_TITLES[position])
                }.attach()

                fab.setIntervalClickListener {
                    when (vpCustomerDetail.currentItem) {
                        INDEX_CHARACTER -> characterService.launchCreateCharacter(
                            this@CustomerDetailActivity,
                            customer
                        )
                        INDEX_ORDER -> orderService.launchCreateOrder(
                            this@CustomerDetailActivity,
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

    private fun handleSideEffect(sideEffect: CustomerDetailSideEffect) {
        when (sideEffect) {
            CustomerDetailSideEffect.DeleteCustomerSuccess -> finish()
            is CustomerDetailSideEffect.DeleteCustomerFailure ->
                binding.root.showSnack(sideEffect.errorMessage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_customer_detail, menu)
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
            .setTitle(getString(R.string.dialog_customer_detail_title))
            .setMessage(getString(R.string.dialog_customer_detail_message_delete, customer.name))
            .setNegativeButton(getString(R.string.cancel)) { it.dismiss() }
            .setPositiveButton(getString(R.string.ok)) { viewModel.deleteCustomer(customer) }
            .show(supportFragmentManager)
    }

    companion object {

        private val TAB_TITLES =
            intArrayOf(R.string.customer_detail_character, R.string.customer_detail_order)

        private const val INDEX_CHARACTER = 0

        private const val INDEX_ORDER = 1

    }
}