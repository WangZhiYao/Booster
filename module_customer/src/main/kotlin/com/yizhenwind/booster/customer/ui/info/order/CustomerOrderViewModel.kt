package com.yizhenwind.booster.customer.ui.info.order

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/20
 */
@HiltViewModel
class CustomerOrderViewModel @Inject constructor(
    private val orderService: IOrderService
) : BaseMVIViewModel<CustomerOrderViewState, CustomerOrderSideEffect>() {

    override val container: Container<CustomerOrderViewState, CustomerOrderSideEffect> =
        container(CustomerOrderViewState.Init())

    fun observeOrderListByCustomerId(customerId: Long) {
        intent {
            orderService.observeOrderListByCustomerId(customerId)
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CustomerOrderViewState.Init(it)
                    }
                }
        }
    }

}