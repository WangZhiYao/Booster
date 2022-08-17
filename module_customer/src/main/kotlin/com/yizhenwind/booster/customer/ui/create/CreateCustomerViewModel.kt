package com.yizhenwind.booster.customer.ui.create

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.data.domain.CreateCustomerUseCase
import com.yizhenwind.booster.customer.data.domain.GetContactTypeListUseCase
import com.yizhenwind.booster.customer.data.domain.GetCustomerByContactUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/3/17
 */
@HiltViewModel
class CreateCustomerViewModel @Inject constructor(
    private val getContactTypeListUseCase: GetContactTypeListUseCase,
    private val getCustomerByContactUseCase: GetCustomerByContactUseCase,
    private val createCustomerUseCase: CreateCustomerUseCase
) : ContainerHost<CreateCustomerViewState, CreateCustomerSideEffect>, BaseViewModel() {

    override val container =
        container<CreateCustomerViewState, CreateCustomerSideEffect>(CreateCustomerViewState.Init())

    private val contactTypeList: ArrayList<ContactType> = ArrayList()

    var contactType: ContactType? = null

    init {
        intent {
            getContactTypeListUseCase()
                .collect {
                    reduce {
                        contactTypeList.clear()
                        contactTypeList.addAll(it)
                        CreateCustomerViewState.Init(it)
                    }
                }
        }
    }

    fun findContact(
        contactType: ContactType?,
        contact: String?
    ) {
        intent {
            if (contactType == null || contact.isNullOrEmpty()) {
                return@intent
            }

            getCustomerByContactUseCase(contactType, contact)
                .ifNullOrElse({
                    postSideEffect(CreateCustomerSideEffect.HideContactError)
                }, {
                    postSideEffect(CreateCustomerSideEffect.ShowContactError(R.string.error_customer_contact_exist))
                })
        }
    }

    fun onContactTypeSelected(contactType: ContactType) {
        intent {
            this@CreateCustomerViewModel.contactType = contactType
            postSideEffect(CreateCustomerSideEffect.HideContactTypeError)
        }
    }

    fun createCustomer(
        name: String?,
        contactType: ContactType?,
        contact: String?,
        remark: String?
    ) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreateCustomerSideEffect.ShowNameError(R.string.error_customer_input_name))
                return@intent
            }

            contactType ?: run {
                postSideEffect(CreateCustomerSideEffect.ShowContactTypeError(R.string.error_customer_select_contact_type))
                return@intent
            }

            if (contact.isNullOrBlank()) {
                postSideEffect(CreateCustomerSideEffect.ShowContactError(R.string.error_customer_input_contact))
                return@intent
            }

            getCustomerByContactUseCase(contactType, contact)
                .ifNullOrElse({
                    postSideEffect(CreateCustomerSideEffect.HideContactError)
                }, {
                    postSideEffect(CreateCustomerSideEffect.ShowContactError(R.string.error_customer_contact_exist))
                    return@intent
                })

            createCustomerUseCase(name, contactType, contact, remark)
                .catch {
                    Timber.e(it)
                    postSideEffect(
                        CreateCustomerSideEffect.CreateCustomerFailure(R.string.error_create_customer)
                    )
                }
                .collect { customer ->
                    customer.ifNullOrElse({
                        postSideEffect(
                            CreateCustomerSideEffect.CreateCustomerFailure(R.string.error_create_customer)
                        )
                    }, {
                        reduce {
                            CreateCustomerViewState.CreateCustomerSuccess(it)
                        }
                    })
                }
        }
    }

    fun resetUI() {
        intent {
            reduce {
                CreateCustomerViewState.Reset(contactTypeList)
            }
        }
    }
}