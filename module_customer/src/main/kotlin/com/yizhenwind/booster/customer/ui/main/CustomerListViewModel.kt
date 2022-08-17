package com.yizhenwind.booster.customer.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.customer.data.domain.ObserveCustomerListUseCase
import javax.inject.Inject

/**
 * 客户列表 ViewModel
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val observeCustomerListUseCase: ObserveCustomerListUseCase
) : ContainerHost<CustomerListViewState, Nothing>, BaseViewModel() {

    override val container = container<CustomerListViewState, Nothing>(CustomerListViewState.Init())

    init {
        intent {
            observeCustomerListUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CustomerListViewState.Init(it)
                    }
                }
        }
    }

}