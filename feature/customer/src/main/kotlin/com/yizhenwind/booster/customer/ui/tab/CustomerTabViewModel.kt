package com.yizhenwind.booster.customer.ui.tab

import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.data.domain.DeleteCustomerByIdUseCase
import com.yizhenwind.booster.customer.data.domain.ObserveCustomerByIdUseCase
import com.yizhenwind.booster.framework.base.BaseMVIViewModel
import com.yizhenwind.booster.logger.Logger
import com.yizhenwind.booster.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@HiltViewModel
class CustomerTabViewModel @Inject constructor(
    private val observeCustomerByIdUseCase: ObserveCustomerByIdUseCase,
    private val deleteCustomerByIdUseCase: DeleteCustomerByIdUseCase
) : BaseMVIViewModel<CustomerTabViewState, CustomerTabSideEffect>() {

    override val container =
        container<CustomerTabViewState, CustomerTabSideEffect>(CustomerTabViewState(Customer()))

    fun getCustomerById(customerId: Long) {
        intent {
            observeCustomerByIdUseCase(customerId)
                .catch {
                    Logger.e(it)
                    postSideEffect(CustomerTabSideEffect.GetCustomerFailure(R.string.error_get_customer_failure))
                }
                .collect {
                    reduce {
                        state.copy(customer = it)
                    }
                }
        }
    }

    fun deleteCustomerById(customerId: Long) {
        intent {
            deleteCustomerByIdUseCase(customerId)
                .catch {
                    Logger.e(it)
                    postSideEffect(CustomerTabSideEffect.DeleteCustomerFailure(R.string.error_delete_customer))
                }
                .collect { success ->
                    if (success) {
                        postSideEffect(CustomerTabSideEffect.DeleteCustomerSuccess)
                    } else {
                        postSideEffect(CustomerTabSideEffect.DeleteCustomerFailure(R.string.error_delete_customer))
                    }
                }
        }
    }
}