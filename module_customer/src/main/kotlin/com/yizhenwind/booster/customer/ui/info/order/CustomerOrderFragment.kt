package com.yizhenwind.booster.customer.ui.info.order

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.BasePagingDataFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2022/6/2
 */
@AndroidEntryPoint
class CustomerOrderFragment :
    BasePagingDataFragment<Order, CustomerOrderAdapter, CustomerOrderViewHolder>() {

    private val viewModel by viewModels<CustomerOrderViewModel>()

    private val args by fragmentArgs(CustomerOrderArgs::deserialize)

    override fun init() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.observeOrderListByCustomerId(args.customerId)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(requireContext())

    override fun getListAdapter(): CustomerOrderAdapter = CustomerOrderAdapter()

    private suspend fun render(state: CustomerOrderViewState) {
        when (state) {
            is CustomerOrderViewState.Init -> adapter.submitData(state.orderList)
        }
    }
}