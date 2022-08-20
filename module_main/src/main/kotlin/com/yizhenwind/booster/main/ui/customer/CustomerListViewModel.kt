package com.yizhenwind.booster.main.ui.customer

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * 客户列表 ViewModel
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val customerService: ICustomerService
) : ContainerHost<CustomerListViewState, Nothing>, BaseViewModel() {

    override val container: Container<CustomerListViewState, Nothing> =
        container(CustomerListViewState.Init())

    init {
        intent {
            customerService.observeCustomerList()
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CustomerListViewState.Init(it)
                    }
                }
        }
    }
}