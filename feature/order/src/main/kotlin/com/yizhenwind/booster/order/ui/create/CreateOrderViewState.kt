package com.yizhenwind.booster.order.ui.create

import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.framework.base.IViewState
import com.yizhenwind.booster.model.CategorySubjectList
import com.yizhenwind.booster.model.CustomerCharacterList
import com.yizhenwind.booster.model.Subject

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
