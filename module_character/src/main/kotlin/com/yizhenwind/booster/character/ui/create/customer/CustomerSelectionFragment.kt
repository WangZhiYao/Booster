package com.yizhenwind.booster.character.ui.create.customer

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.ui.create.CreateCharacterViewModel2
import com.yizhenwind.booster.component.base.BaseListMVIFragment
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
@AndroidEntryPoint
class CustomerSelectionFragment :
    BaseListMVIFragment<CustomerSelectionViewState, CustomerSelectionSideEffect>() {

    private val activityViewModel by activityViewModels<CreateCharacterViewModel2>()
    private val viewModel by viewModels<CustomerSelectionViewModel>()
    private val adapter = CustomerSelectionAdapter()

    override fun initView() {
        binding.rvList.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CustomerSelectionFragment.adapter
        }

        adapter.onCustomerSelectedListener = { customer ->
            activityViewModel.setCustomer(customer)
            findNavController().navigate(R.id.action_nav_customer_selection_to_nav_zone_selection)
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        activityViewModel.customer.observe(viewLifecycleOwner) { customer ->
            adapter.checkedCustomer = customer
        }
    }

    override fun render(state: CustomerSelectionViewState) {
        adapter.customerList = state.customerList
    }
}