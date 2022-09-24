package com.yizhenwind.booster.home.ui.customer

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.framework.base.BaseListMVIFragment
import com.yizhenwind.booster.framework.ext.registerMenu
import com.yizhenwind.booster.framework.widget.BoosterDialog
import com.yizhenwind.booster.framework.widget.action.BottomActionDialog
import com.yizhenwind.booster.home.R
import com.yizhenwind.booster.mediator.customer.ICustomerService
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

    override fun initView() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CustomerSummaryFragment.adapter
        }

        adapter.onItemClickListener = { customerSummary ->
            customerService.launchCustomerTab(
                requireContext(),
                customerSummary.id,
                Constant.CustomerTab.INDEX_DETAIL
            )
        }

        adapter.onMoreActionClickListener = { customerSummary ->
            customerSummary.apply {
                BottomActionDialog.Builder<CustomerSummaryAction>()
                    .setTitle(name)
                    .setActions(
                        listOf(
                            CustomerSummaryAction.CreateCharacter,
                            CustomerSummaryAction.CreateOrder,
                            CustomerSummaryAction.Delete
                        )
                    )
                    .setOnActionClickListener { dialog, action ->
                        when (action) {
                            is CustomerSummaryAction.CreateCharacter -> {}
                            is CustomerSummaryAction.CreateOrder -> {}
                            is CustomerSummaryAction.Delete -> {
                                attemptDeleteCustomer(id, name)
                                dialog.dismiss()
                            }
                        }
                    }
                    .show(childFragmentManager)
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