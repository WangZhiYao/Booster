package com.yizhenwind.booster.main.ui.order

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/21
 */
@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val orderService: IOrderService
) : BaseMVIViewModel<OrderListViewState, OrderListSideEffect>() {

    override val container: Container<OrderListViewState, OrderListSideEffect> =
        container(OrderListViewState.Init())

    init {
        intent {
            orderService.observeOrderList()
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        OrderListViewState.Init(it)
                    }
                }
        }
    }
}