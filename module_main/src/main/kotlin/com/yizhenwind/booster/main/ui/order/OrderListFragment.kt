package com.yizhenwind.booster.main.ui.order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.BasePagingDataMVIFragment
import com.yizhenwind.booster.component.ext.registerMenu
import com.yizhenwind.booster.main.R
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
@AndroidEntryPoint
class OrderListFragment :
    BasePagingDataMVIFragment<Order, OrderAdapter, OrderViewHolder, OrderListViewState, OrderListSideEffect>() {

    override val viewModel by viewModels<OrderListViewModel>()
    override val adapter: OrderAdapter = OrderAdapter()

    @Inject
    lateinit var orderService: IOrderService

    override fun initPage() {
        super.initPage()
        initView()
    }

    private fun initView() {
        registerMenu(R.menu.menu_order_list) { menuItem ->
            return@registerMenu when (menuItem.itemId) {
                R.id.action_search -> {
                    // TODO: search order
                    true
                }
                R.id.action_category_list -> {
                    orderService.launchCategoryList(requireContext())
                    true
                }
                else -> false
            }
        }
    }

    override fun render(state: OrderListViewState) {
        when (state) {
            is OrderListViewState.Init -> {
                lifecycleScope.launch {
                    adapter.submitData(state.orderList)
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: OrderListSideEffect) {

    }
}