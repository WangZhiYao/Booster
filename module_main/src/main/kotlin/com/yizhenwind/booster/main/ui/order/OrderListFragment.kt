package com.yizhenwind.booster.main.ui.order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yizhenwind.booster.component.base.BaseListMVIFragment
import com.yizhenwind.booster.component.ext.registerMenu
import com.yizhenwind.booster.main.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
@AndroidEntryPoint
class OrderListFragment : BaseListMVIFragment<OrderListViewState, OrderListSideEffect>() {

    private val viewModel by viewModels<OrderListViewModel>()
    private val adapter: OrderAdapter = OrderAdapter()

    override fun initView() {
        registerMenu(R.menu.menu_order_list) { menuItem ->
            return@registerMenu when (menuItem.itemId) {
                R.id.action_search -> {
                    // TODO: search order
                    true
                }
                else -> false
            }
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
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
}