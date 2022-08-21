package com.yizhenwind.booster.main.ui.customer

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.BasePagingDataMVIFragment
import com.yizhenwind.booster.component.ext.registerMenu
import com.yizhenwind.booster.main.R
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 客户列表
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class CustomerListFragment :
    BasePagingDataMVIFragment<Customer, CustomerAdapter, CustomerViewHolder, CustomerListViewState, CustomerListSideEffect>() {

    override val viewModel by viewModels<CustomerListViewModel>()
    override val adapter: CustomerAdapter = CustomerAdapter()

    @Inject
    lateinit var customerService: ICustomerService

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    override fun initPage() {
        super.initPage()
        initView()
    }

    private fun initView() {
        adapter.onItemClickListener = { customer ->
            customerService.launchCustomerInfo(requireContext(), customer)
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

    override fun render(state: CustomerListViewState) {
        when (state) {
            is CustomerListViewState.Init -> {
                lifecycleScope.launch {
                    adapter.submitData(state.customerList)
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: CustomerListSideEffect) {

    }
}