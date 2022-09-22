package com.yizhenwind.booster.home.ui.customer

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.framework.base.BaseListMVIFragment
import com.yizhenwind.booster.framework.ext.registerMenu
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
class CustomerFragment :
    BaseListMVIFragment<CustomerViewState, CustomerSideEffect>() {

    private val viewModel by viewModels<CustomerViewModel>()
    private val adapter: CustomerAdapter = CustomerAdapter()

    @Inject
    lateinit var customerService: ICustomerService

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    override fun initView() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CustomerFragment.adapter
        }

        adapter.onItemClickListener = { customer ->
            customerService.launchCustomerTab(
                requireContext(),
                customer,
                Constant.CustomerTab.INDEX_DETAIL
            )
        }

        adapter.onCreateCharacterClickListener = { customer ->
            characterService.launchCreateCharacter(requireContext(), customer, true)
        }

        adapter.onCreateOrderClickListener = { customer ->
            orderService.launchCreateOrder(requireContext(), customer.id)
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

    override fun render(state: CustomerViewState) {
        when (state) {
            is CustomerViewState.Init -> {
                lifecycleScope.launch {
                    adapter.submitData(state.customerList)
                }
            }
        }
    }
}