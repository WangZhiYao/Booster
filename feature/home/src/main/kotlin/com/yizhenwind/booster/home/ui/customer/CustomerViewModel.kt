package com.yizhenwind.booster.home.ui.customer

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.framework.base.BaseMVIViewModel
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
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
class CustomerViewModel @Inject constructor(
    private val customerService: ICustomerService
) : BaseMVIViewModel<CustomerViewState, CustomerSideEffect>() {

    override val container: Container<CustomerViewState, CustomerSideEffect> =
        container(CustomerViewState.Init())

    init {
        intent {
            customerService.observeCustomerList()
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CustomerViewState.Init(it)
                    }
                }
        }
    }
}