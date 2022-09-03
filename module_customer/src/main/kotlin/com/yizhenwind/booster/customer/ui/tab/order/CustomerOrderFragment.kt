package com.yizhenwind.booster.customer.ui.tab.order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.component.base.BaseListMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2022/6/2
 */
@AndroidEntryPoint
class CustomerOrderFragment :
    BaseListMVIFragment<CustomerOrderViewState, CustomerOrderSideEffect>() {

    private val viewModel by viewModels<CustomerOrderViewModel>()
    private val args by fragmentArgs<CustomerOrderArgs>()
    private val adapter: CustomerOrderAdapter = CustomerOrderAdapter()

    override fun initView() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CustomerOrderFragment.adapter
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.observeOrderListByCustomerId(args.customerId)
    }

    override fun render(state: CustomerOrderViewState) {
        when (state) {
            is CustomerOrderViewState.Init -> {
                lifecycleScope.launch {
                    adapter.submitData(state.orderList)
                }
            }
        }
    }
}