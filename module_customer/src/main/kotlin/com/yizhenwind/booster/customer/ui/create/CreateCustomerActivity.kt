package com.yizhenwind.booster.customer.ui.create

import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.ext.blankThenNull
import com.yizhenwind.booster.component.base.BaseTextInputActivity
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.ext.showSnackWithAction
import com.yizhenwind.booster.customer.R
import com.yizhenwind.booster.customer.databinding.ActivityCreateCustomerBinding
import com.yizhenwind.booster.mediator.customer.ICustomerService
import javax.inject.Inject

/**
 * 创建客户
 *
 * @author WangZhiYao
 * @since 2022/3/14
 */
@AndroidEntryPoint
class CreateCustomerActivity :
    BaseTextInputActivity<ActivityCreateCustomerBinding>(ActivityCreateCustomerBinding::inflate) {

    private val viewModel by viewModels<CreateCustomerViewModel>()

    @Inject
    lateinit var customerService: ICustomerService

    private lateinit var contactTypeAdapter: ContactTypeAdapter

    override fun init() {
        initView()
        initData()
    }

    override fun showBack() = true

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

    private fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    private fun render(state: CreateCustomerViewState) {
        when (state) {
            is CreateCustomerViewState.Init -> initContactTypeSelector(state.contactTypeList)
            is CreateCustomerViewState.CreateCustomerSuccess -> {
                viewModel.resetUI()
                binding.root.showSnackWithAction(
                    R.string.create_customer_success,
                    R.string.create_customer_jump_to_detail
                ) {
                    customerService.launchCustomerDetail(
                        this@CreateCustomerActivity,
                        state.customer
                    )
                    finish()
                }
            }
            is CreateCustomerViewState.Reset -> {
                initContactTypeSelector(state.contactTypeList)
                resetUI()
            }
        }
    }

    private fun handleSideEffect(sideEffect: CreateCustomerSideEffect) {
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
            contactTypeAdapter = ContactTypeAdapter(this@CreateCustomerActivity, this)

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
}