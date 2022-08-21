package com.yizhenwind.booster.customer.ui.info.order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.BasePagingDataMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 *
 * @author WangZhiYao
 * @since 2022/6/2
 */
@AndroidEntryPoint
class CustomerOrderFragment :
    BasePagingDataMVIFragment<Order, CustomerOrderAdapter, CustomerOrderViewHolder, CustomerOrderViewState, CustomerOrderSideEffect>() {

    override val viewModel by viewModels<CustomerOrderViewModel>()
    override val adapter: CustomerOrderAdapter = CustomerOrderAdapter()

    private val args by fragmentArgs(CustomerOrderArgs::deserialize)

    override fun initPage() {
        super.initPage()
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

    override fun handleSideEffect(sideEffect: CustomerOrderSideEffect) {

    }
}