package com.yizhenwind.booster.customer.ui.create

import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/3/28
 */
sealed class CreateCustomerViewState : IViewState {

    data class Init(val contactTypeList: List<ContactType> = emptyList()) :
        CreateCustomerViewState()

    data class Reset(val contactTypeList: List<ContactType>) : CreateCustomerViewState()

    data class OnContactTypeSelected(val contactType: ContactType) : CreateCustomerViewState()

    data class CreateCustomerSuccess(val customer: Customer) : CreateCustomerViewState()
}