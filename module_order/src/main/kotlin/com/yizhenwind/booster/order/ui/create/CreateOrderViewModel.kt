package com.yizhenwind.booster.order.ui.create

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.common.model.*
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.mediator.customer.ICustomerService
import com.yizhenwind.booster.order.data.domain.GetBillingCycleListUseCase
import com.yizhenwind.booster.order.data.domain.GetCategorySubjectListUseCase
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
    private val getCategorySubjectListUseCase: GetCategorySubjectListUseCase,
    private val getBillingCycleListUseCase: GetBillingCycleListUseCase
) : ContainerHost<CreateOrderViewState, CreateOrderSideEffect>, BaseViewModel() {

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
                .combine(getCategorySubjectListUseCase()) { customerCharacterList, categorySubjectList ->
                    CreateOrderViewState.Init(customerCharacterList, categorySubjectList).also {
                        this@CreateOrderViewModel.customerCharacterList.addAll(customerCharacterList)
                        this@CreateOrderViewModel.categorySubjectList.addAll(categorySubjectList)
                    }
                }
                .combine(getBillingCycleListUseCase()) { state, billingCycleList ->
                    state.copy(billingCycleList = billingCycleList)
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