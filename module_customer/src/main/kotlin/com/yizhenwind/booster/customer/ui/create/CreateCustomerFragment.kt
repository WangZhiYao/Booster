package com.yizhenwind.booster.customer.ui.create

import android.view.inputmethod.EditorInfo
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.ext.blankThenNull
import com.yizhenwind.booster.component.base.BaseMVIFragment
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.ext.showSnackWithAction
import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.databinding.FragmentCreateCustomerBinding
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/31
 */
@AndroidEntryPoint
class CreateCustomerFragment :
    BaseMVIFragment<FragmentCreateCustomerBinding, CreateCustomerViewState, CreateCustomerSideEffect, CreateCustomerViewModel>(
        FragmentCreateCustomerBinding::inflate
    ) {

    override val viewModel by viewModels<CreateCustomerViewModel>()

    private lateinit var contactTypeAdapter: ContactTypeAdapter

    override fun initPage() {
        super.initPage()
        initView()
    }

    private fun initView() {
        binding.apply {
            tietCreateCustomerName.doAfterTextChanged { name ->
                if (!name.isNullOrBlank()) {
                    tilCreateCustomerName.error = null
                }
            }

            actvCreateCustomerContactType.apply {
                setOnItemClickListener { _, _, position, _ ->
                    tietCreateCustomerContactValue.apply {
                        isEnabled = true
                        text = null
                        inputType =
                            when (ContactType.values()[position]) {
                                ContactType.QQ, ContactType.PHONE -> EditorInfo.TYPE_CLASS_NUMBER
                                ContactType.WECHAT -> EditorInfo.TYPE_CLASS_TEXT
                            }
                    }
                }
            }

            tietCreateCustomerContactValue.doAfterTextChanged { contact ->
                viewModel.findContact(
                    actvCreateCustomerContactType.text?.toString()
                        ?.let { text ->
                            ContactType.values().firstOrNull { it.value == text }
                        },
                    contact?.toString()
                )
            }

            fabCreateCustomerSubmit.setOnClickListener { attemptCreateCustomer() }
        }
    }


    override fun render(state: CreateCustomerViewState) {
        when (state) {
            is CreateCustomerViewState.Init -> initContactTypeSelector(state.contactTypeList)
            is CreateCustomerViewState.CreateCustomerSuccess -> viewModel.resetUI()
            is CreateCustomerViewState.Reset -> {
                initContactTypeSelector(state.contactTypeList)
                resetUI()
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreateCustomerSideEffect) {
        when (sideEffect) {
            is CreateCustomerSideEffect.ShowNameError ->
                showNameError(sideEffect.errorMessage)
            CreateCustomerSideEffect.HideNameError ->
                binding.tilCreateCustomerName.error = null
            is CreateCustomerSideEffect.ShowContactTypeError ->
                showContactTypeError(sideEffect.errorMessage)
            CreateCustomerSideEffect.HideContactTypeError ->
                binding.tilCreateCustomerContactType.error = null
            is CreateCustomerSideEffect.ShowContactError ->
                showContactError(sideEffect.errorMessage)
            CreateCustomerSideEffect.HideContactError ->
                binding.tilCreateCustomerContactValue.error = null
            is CreateCustomerSideEffect.CreateCustomerFailure ->
                binding.root.showSnack(sideEffect.errorMessage)
        }
    }

    private fun attemptCreateCustomer() {
        binding.apply {
            val name = tietCreateCustomerName.text?.toString()
            val contactType =
                actvCreateCustomerContactType.text?.toString()
                    ?.let { text -> ContactType.values().firstOrNull { it.value == text } }
            val contact = tietCreateCustomerContactValue.text?.toString()
            val remark = tietCreateCustomerRemark.text?.toString()
            viewModel.createCustomer(name, contactType, contact, remark.blankThenNull())
        }
    }

    private fun initContactTypeSelector(contactTypeList: List<ContactType>) {
        contactTypeList.apply {
            contactTypeAdapter = ContactTypeAdapter(requireContext(), this)

            binding.actvCreateCustomerContactType.apply {
                setAdapter(contactTypeAdapter)
                text = null
                firstOrNull()?.let {
                    setText(it.value, false)
                    viewModel.onContactTypeSelected(it)
                }
            }
        }
    }

    private fun resetUI() {
        binding.apply {
            tietCreateCustomerName.apply {
                text = null
                requestFocus()
            }
            actvCreateCustomerContactType.text = null
            tietCreateCustomerContactValue.apply {
                text = null
                isEnabled = false
            }
            tietCreateCustomerRemark.text = null
        }
    }

    private fun showNameError(@StringRes errorMessage: Int) {
        binding.apply {
            showErrorInfo(
                tilCreateCustomerName,
                errorMessage,
                tietCreateCustomerName
            )
        }
    }

    private fun showContactTypeError(@StringRes errorMessage: Int) {
        showErrorInfo(
            binding.tilCreateCustomerContactType,
            errorMessage
        )
    }

    private fun showContactError(@StringRes errorMessage: Int) {
        binding.apply {
            showErrorInfo(
                tilCreateCustomerContactValue,
                errorMessage,
                tietCreateCustomerContactValue
            )
        }
    }

    private fun showErrorInfo(
        textInputLayout: TextInputLayout,
        @StringRes errorMessage: Int,
        textInputEditText: TextInputEditText? = null
    ) {
        textInputLayout.error = getString(errorMessage)
        textInputEditText?.requestFocus()
    }
}