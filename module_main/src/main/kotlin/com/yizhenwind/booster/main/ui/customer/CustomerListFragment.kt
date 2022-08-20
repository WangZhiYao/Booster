package com.yizhenwind.booster.main.ui.customer

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.main.R
import com.yizhenwind.booster.main.databinding.FragmentCustomerListBinding
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 * 客户列表
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class CustomerListFragment :
    BaseFragment<FragmentCustomerListBinding>(FragmentCustomerListBinding::inflate) {

    private val viewModel by viewModels<CustomerListViewModel>()
    private val adapter: CustomerAdapter by lazy { CustomerAdapter() }

    @Inject
    lateinit var customerService: ICustomerService

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    override fun init() {
        initView()
        initData()
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

        binding.apply {
            rvCustomer.adapter = adapter
        }

        inflateMenu()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private suspend fun render(state: CustomerListViewState) {
        when (state) {
            is CustomerListViewState.Init -> adapter.submitData(state.customerList)
        }
    }

    private fun inflateMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_customer_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_search) {
                    // TODO add search
                    return true
                }
                return false
            }
        })
    }
}