package com.yizhenwind.booster.home.ui.customer

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.util.ContactHelper
import com.yizhenwind.booster.framework.base.BaseListMVIFragment
import com.yizhenwind.booster.framework.ext.registerMenu
import com.yizhenwind.booster.framework.ext.showSnack
import com.yizhenwind.booster.framework.widget.BoosterDialog
import com.yizhenwind.booster.home.R
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 * 客户列表
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class CustomerSummaryFragment :
    BaseListMVIFragment<CustomerSummaryViewState, CustomerSummarySideEffect>() {

    private val viewModel by viewModels<CustomerSummaryViewModel>()
    private val adapter: CustomerSummaryAdapter = CustomerSummaryAdapter()

    @Inject
    lateinit var customerService: ICustomerService

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    override fun initView() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CustomerSummaryFragment.adapter
        }

        adapter.apply {
            onItemClickListener = { customerSummary ->
                customerService.launchCustomerTab(
                    requireContext(),
                    customerSummary.id,
                    Constant.CustomerTab.INDEX_DETAIL
                )
            }
            onCreateCharacterClickListener = { customerId ->
                characterService.launchCreateCharacter(requireContext(), customerId)
            }
            onCreateOrderClickListener = { customerId ->
                orderService.launchCreateOrder(requireContext(), customerId)
            }
            onContactClickListener = { contactType, contact ->
                when (contactType) {
                    ContactType.QQ -> {
                        if (!ContactHelper.attemptLaunchQQ(requireContext(), contact)) {
                            binding.root.showSnack(R.string.error_launch_qq)
                        }
                    }
                    ContactType.WECHAT -> {
                        if (!ContactHelper.attemptLaunchWeChat(requireContext())) {
                            binding.root.showSnack(R.string.error_launch_wechat)
                        }
                    }
                    ContactType.PHONE -> {
                        if (!ContactHelper.attemptLaunchDial(requireContext(), contact)) {
                            binding.root.showSnack(R.string.error_launch_phone)
                        }
                    }
                }
            }
            onDeleteCustomerClickListener = { customerId, customerName ->
                attemptDeleteCustomer(customerId, customerName)
            }
        }

        registerMenu(R.menu.menu_customer_list) { menuItem ->
            return@registerMenu when (menuItem.itemId) {
                R.id.action_search -> {
                    // TODO add search
                    true
                }
                else -> false
            }
        }

    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun render(state: CustomerSummaryViewState) {
        when (state) {
            is CustomerSummaryViewState.Init -> {
                lifecycleScope.launch {
                    adapter.submitData(state.customerSummaryList)
                }
            }
        }
    }

    private fun attemptDeleteCustomer(customerId: Long, customerName: String) {
        BoosterDialog.Builder()
            .setTitle(getString(R.string.dialog_customer_summary_title))
            .setMessage(getString(R.string.dialog_customer_summary_message_delete, customerName))
            .setNegativeButton(getString(R.string.cancel)) { it.dismiss() }
            .setPositiveButton(getString(R.string.ok)) { viewModel.deleteCustomerById(customerId) }
            .show(childFragmentManager)
    }
}