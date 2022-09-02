package com.yizhenwind.booster.customer.ui.info

import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.data.domain.DeleteCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@HiltViewModel
class CustomerInfoViewModel @Inject constructor(
    private val deleteCustomerUseCase: DeleteCustomerUseCase
) : BaseMVIViewModel<CustomerInfoViewState, CustomerInfoSideEffect>() {

    override val container =
        container<CustomerInfoViewState, CustomerInfoSideEffect>(CustomerInfoViewState.Init)

    fun deleteCustomer(customer: Customer) {
        intent {
            deleteCustomerUseCase(customer)
                .catch {
                    Timber.e(it)
                    postSideEffect(CustomerInfoSideEffect.DeleteCustomerFailure(R.string.error_delete_customer))
                }
                .collect { success ->
                    if (success) {
                        postSideEffect(CustomerInfoSideEffect.DeleteCustomerSuccess)
                    } else {
                        postSideEffect(CustomerInfoSideEffect.DeleteCustomerFailure(R.string.error_delete_customer))
                    }
                }
        }
    }
}