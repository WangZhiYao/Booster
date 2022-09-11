package com.yizhenwind.booster.order.ui.create

import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.common.model.*
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.order.data.domain.GetBillingCycleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * 创建订单 ViewModel
 *
 * @author WangZhiYao
 * @since 2022/6/18
 */
@HiltViewModel
class CreateOrderViewModel @Inject constructor(
    private val customerService: ICustomerService,
    private val getBillingCycleListUseCase: GetBillingCycleListUseCase
) : BaseMVIViewModel<CreateOrderViewState, CreateOrderSideEffect>() {

    override val container =
        container<CreateOrderViewState, CreateOrderSideEffect>(CreateOrderViewState.Init())

    private val customerCharacterList: ArrayList<CustomerCharacterList> = ArrayList()
    private val categorySubjectList: ArrayList<CategorySubjectList> = ArrayList()

    var customer: Customer? = null
    var character: CustomerCharacterList.Character? = null
    var category: Category? = null
    var subject: Subject? = null
    var billingCycle: BillingCycle? = null

    init {
        intent {
            customerService.getCustomerWithCharacterList()
                .combine(getBillingCycleListUseCase()) { customerCharacterList, billingCycleList ->
                    this@CreateOrderViewModel.customerCharacterList.addAll(customerCharacterList)
                    CreateOrderViewState.Init(
                        customerCharacterList = customerCharacterList,
                        billingCycleList = billingCycleList
                    )
                }
                .collect {
                    reduce {
                        it
                    }
                }
        }
    }

    fun onCustomerSelected(customer: Customer) {
        intent {
            this@CreateOrderViewModel.customer = customer

            reduce {

            CreateOrderViewState.OnCustomerSelected(
                    customerCharacterList.first { it.customer == customer }.characterList
                )
            }
        }
    }

    fun onCharacterSelected(character: CustomerCharacterList.Character) {
        this@CreateOrderViewModel.character = character
    }

    fun onCategorySelected(category: Category) {
        intent {
            this@CreateOrderViewModel.category = category
            reduce {
                CreateOrderViewState.OnCategorySelected(
                    categorySubjectList.first { it.category == category }.subjectList
                )
            }
        }
    }

    fun onSubjectSelected(subject: Subject) {
        this@CreateOrderViewModel.subject = subject
    }

    fun onBillingCycleSelected(billingCycle: BillingCycle) {
        this@CreateOrderViewModel.billingCycle = billingCycle
    }
}