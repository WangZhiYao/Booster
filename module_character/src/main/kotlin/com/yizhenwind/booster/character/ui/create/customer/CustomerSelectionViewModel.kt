package com.yizhenwind.booster.character.ui.create.customer

import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
@HiltViewModel
class CustomerSelectionViewModel @Inject constructor(
    private val customerService: ICustomerService,
) : BaseMVIViewModel<CustomerSelectionViewState, CustomerSelectionSideEffect>() {

    override val container =
        container<CustomerSelectionViewState, CustomerSelectionSideEffect>(
            CustomerSelectionViewState()
        )

    init {
        intent {
            customerService.getCustomerList()
                .collect { customerList ->
                    reduce {
                        state.copy(customerList = customerList)
                    }
                }
        }
    }
}