package com.yizhenwind.booster.home.ui.customer

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.framework.base.BaseMVIViewModel
import com.yizhenwind.booster.home.R
import com.yizhenwind.booster.logger.Logger
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
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
class CustomerSummaryViewModel @Inject constructor(
    private val customerService: ICustomerService
) : BaseMVIViewModel<CustomerSummaryViewState, CustomerSummarySideEffect>() {

    override val container: Container<CustomerSummaryViewState, CustomerSummarySideEffect> =
        container(CustomerSummaryViewState.Init())

    init {
        intent {
            customerService.observeCustomerSummaryList()
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CustomerSummaryViewState.Init(it)
                    }
                }
        }
    }

    fun deleteCustomerById(customerId: Long) {
        intent {
            customerService.deleteCustomerById(customerId)
                .catch {
                    Logger.e(it)
                    postSideEffect(CustomerSummarySideEffect.DeleteCustomerFailure(R.string.error_delete_customer))
                }
                .collect { success ->
                    if (success) {
                        postSideEffect(CustomerSummarySideEffect.DeleteCustomerSuccess)
                    } else {
                        postSideEffect(CustomerSummarySideEffect.DeleteCustomerFailure(R.string.error_delete_customer))
                    }
                }
        }
    }
}