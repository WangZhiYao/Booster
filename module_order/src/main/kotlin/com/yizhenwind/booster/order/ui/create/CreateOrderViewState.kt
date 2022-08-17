package com.yizhenwind.booster.order.ui.create

import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.common.model.CategorySubjectList
import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.base.IViewState

/**
 * 创建订单 ViewState
 *
 * @author WangZhiYao
 * @since 2022/6/18
 */
sealed class CreateOrderViewState : IViewState {

    data class Init(
        val customerCharacterList: List<CustomerCharacterList> = emptyList(),
        val categorySubjectList: List<CategorySubjectList> = emptyList(),
        val billingCycleList: List<BillingCycle> = emptyList()
    ) : CreateOrderViewState()

    data class OnCustomerSelected(val characterList: List<CustomerCharacterList.Character>) :
        CreateOrderViewState()

    data class OnCategorySelected(val subjectList: List<Subject>) :
        CreateOrderViewState()

}
