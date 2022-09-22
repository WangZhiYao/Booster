package com.yizhenwind.booster.customer.ui.tab

import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.data.domain.DeleteCustomerUseCase
import com.yizhenwind.booster.framework.base.BaseMVIViewModel
import com.yizhenwind.booster.logger.Logger
import com.yizhenwind.booster.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@HiltViewModel
class CustomerTabViewModel @Inject constructor(
    private val deleteCustomerUseCase: DeleteCustomerUseCase
) : BaseMVIViewModel<CustomerTabViewState, CustomerTabSideEffect>() {

    override val container =
        container<CustomerTabViewState, CustomerTabSideEffect>(CustomerTabViewState.Init)

    fun deleteCustomer(customer: Customer) {
        intent {
            deleteCustomerUseCase(customer)
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