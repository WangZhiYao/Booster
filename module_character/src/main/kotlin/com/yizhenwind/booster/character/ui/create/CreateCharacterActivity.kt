package com.yizhenwind.booster.character.ui.create

import android.text.method.DigitsKeyListener
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yizhenwind.booster.character.databinding.ActivityCreateCharacterBinding
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.ext.blankThenNull
import com.yizhenwind.booster.common.ext.findFirstOrFirst
import com.yizhenwind.booster.component.base.BaseMVIActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.ext.viewBindings
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 * 创建角色
 *
 * @author WangZhiYao
 * @since 2022/4/23
 */
@AndroidEntryPoint
class CreateCharacterActivity :
    BaseMVIActivity<CreateCharacterViewState, CreateCharacterSideEffect>() {

    private val viewModel by viewModels<CreateCharacterViewModel>()
    private val binding by viewBindings<ActivityCreateCharacterBinding>()
    private val args by activityArgs<CreateCharacterArgs>()

    @Inject
    lateinit var customerService: ICustomerService

    private lateinit var customerAdapter: CustomerAdapter

    private val zoneAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line)
    }

    private val serverAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line)
    }

    private val sectAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line)
    }

    private val internalAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line)
    }

    override fun getRootView(): View = binding.root

    override fun initPage() {
        super.initPage()
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            toolbar.apply {
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }

            actvCreateCharacterCustomer.setOnItemClickListener { _, _, position, _ ->
                viewModel.customer = customerAdapter.getItem(position)
            }

            actvCreateCharacterZone.apply {
                setAdapter(zoneAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    zoneAdapter.getItem(position)?.let { viewModel.onZoneSelected(it) }
                }
            }

            actvCreateCharacterServer.setAdapter(serverAdapter)

            tietCreateCharacterAccount.apply {
                doAfterTextChanged { account ->
                    account.let {
                        viewModel.onAccountChanged(it?.toString())
                    }
                }
            }

            tietCreateCharacterPassword.apply {
                keyListener = DIGITS_KEY_LISTENER
                doAfterTextChanged { password ->
                    viewModel.onPasswordChanged(password?.toString())
                }
            }

            tietCreateCharacterSecurityLock.apply {
                keyListener = DIGITS_KEY_LISTENER
                doAfterTextChanged {
                    viewModel.onSecurityLockChanged(it?.toString())
                }
            }

            tietCreateCharacterName.apply {
                /*filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                    return@InputFilter if (source.matches(Constant.Regex.CHARACTER_NAME)) null else ""
                })*/
                doAfterTextChanged { characterName ->
                    viewModel.onCharacterNameChanged(characterName?.toString())
                }
            }

            actvCreateCharacterSect.apply {
                setAdapter(sectAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    sectAdapter.getItem(position)?.let { viewModel.onSectSelected(it) }
                }
            }

            actvCreateCharacterInternal.setAdapter(internalAdapter)

            fab.setIntervalClickListener { attemptCreateCharacter() }
        }
    }

    private fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
        args.customer?.let { viewModel.customer = it }
    }

    override fun render(state: CreateCharacterViewState) {
        when (state) {
            is CreateCharacterViewState.CreateCharacterSuccess -> {
                viewModel.customer?.let { customer ->
                    if (args.openDetailAfterCreateSuccess) {
                        customerService.launchCustomerTab(
                            this,
                            customer,
                            Constant.CustomerTab.INDEX_CHARACTER
                        )
                    }
                }
                finish()
            }
            is CreateCharacterViewState.Init -> {
                state.apply {
                    customerList.let {
                        customerAdapter = CustomerAdapter(this@CreateCharacterActivity, it)
                        val customer = it.findFirstOrFirst { customer ->
                            customer.id == viewModel.customer?.id
                        }

                        binding.actvCreateCharacterCustomer.apply {
                            setAdapter(customerAdapter)
                            text = null
                            setText(customer?.name, false)
                        }
                    }

                    zoneAdapter.apply {
                        clear()
                        addAll(zoneServerList.map { it.zone.name })
                    }

                    sectAdapter.apply {
                        clear()
                        addAll(sectInternalList.map { it.sect.name })
                    }
                }
            }
            is CreateCharacterViewState.OnZoneSelected -> {
                serverAdapter.apply {
                    clear()
                    state.serverList.apply {
                        addAll(map { it.name })
                        binding.actvCreateCharacterServer.setText(first().name, false)
                    }
                }
            }
            is CreateCharacterViewState.OnSectSelected -> {
                internalAdapter.apply {
                    clear()
                    state.internalList.apply {
                        addAll(map { it.name })
                        binding.actvCreateCharacterInternal.setText(first().name, false)
                    }
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreateCharacterSideEffect) {
        sideEffect.let {
            binding.apply {
                when (it) {
                    is CreateCharacterSideEffect.ShowCustomerError ->
                        showCustomerError(it.errorMessage)
                    CreateCharacterSideEffect.HideCustomerError ->
                        tilCreateCharacterCustomer.error = null
                    is CreateCharacterSideEffect.ShowZoneError ->
                        showZoneError(it.errorMessage)
                    CreateCharacterSideEffect.HideZoneError ->
                        tilCreateCharacterZone.error = null
                    is CreateCharacterSideEffect.ShowServerError ->
                        showServerError(it.errorMessage)
                    CreateCharacterSideEffect.HideServerError ->
                        tilCreateCharacterServer.error = null
                    is CreateCharacterSideEffect.ShowAccountError ->
                        showAccountError(it.errorMessage)
                    CreateCharacterSideEffect.HideAccountError ->
                        tilCreateCharacterAccount.error = null
                    is CreateCharacterSideEffect.ShowPasswordError ->
                        showPasswordError(it.errorMessage)
                    CreateCharacterSideEffect.HidePasswordError ->
                        tilCreateCharacterPassword.error = null
                    is CreateCharacterSideEffect.ShowSecurityLockError ->
                        showSecurityLock(it.errorMessage)
                    CreateCharacterSideEffect.HideSecurityLockError ->
                        tilCreateCharacterSecurityLock.error = null
                    is CreateCharacterSideEffect.ShowCharacterNameError ->
                        showCharacterNameError(it.errorMessage)
                    CreateCharacterSideEffect.HideCharacterNameError ->
                        tilCreateCharacterName.error = null
                    is CreateCharacterSideEffect.ShowSectError ->
                        showSectError(it.errorMessage)
                    CreateCharacterSideEffect.HideSectError ->
                        tilCreateCharacterSect.error = null
                    is CreateCharacterSideEffect.ShowInternalError ->
                        showInternalError(it.errorMessage)
                    CreateCharacterSideEffect.HideInternalError ->
                        tilCreateCharacterInternal.error = null
                    is CreateCharacterSideEffect.CreateCharacterFailed ->
                        root.showSnack(it.errorMessage)
                }
            }
        }
    }

    private fun attemptCreateCharacter() {
        binding.apply {
            val zone = actvCreateCharacterZone.text.toString()
            val server = actvCreateCharacterServer.text.toString()
            val account = tietCreateCharacterAccount.text?.toString()
            val password = tietCreateCharacterPassword.text?.toString()
            val securityLock = tietCreateCharacterSecurityLock.text?.toString()
            val characterName = tietCreateCharacterName.text?.toString()
            val sect = actvCreateCharacterSect.text.toString()
            val internal = actvCreateCharacterInternal.text.toString()
            val remark = tietCreateCharacterRemark.text?.toString()

            viewModel.attemptCreateCharacter(
                viewModel.customer?.id,
                zone,
                server,
                account,
                password,
                securityLock.blankThenNull(),
                characterName,
                sect,
                internal,
                remark.blankThenNull()
            )
        }
    }

    private fun showCustomerError(@StringRes errorMessage: Int) {
        showErrorInfo(binding.tilCreateCharacterCustomer, errorMessage)
    }

    private fun showZoneError(@StringRes errorMessage: Int) {
        showErrorInfo(binding.tilCreateCharacterZone, errorMessage)
    }

    private fun showServerError(@StringRes errorMessage: Int) {
        showErrorInfo(binding.tilCreateCharacterServer, errorMessage)
    }

    private fun showAccountError(@StringRes errorMessage: Int) {
        binding.apply {
            showErrorInfo(tilCreateCharacterAccount, errorMessage, tietCreateCharacterAccount)
        }
    }

    private fun showPasswordError(@StringRes errorMessage: Int) {
        binding.apply {
            showErrorInfo(tilCreateCharacterPassword, errorMessage, tietCreateCharacterPassword)
        }
    }

    private fun showSecurityLock(@StringRes errorMessage: Int) {
        binding.apply {
            showErrorInfo(
                tilCreateCharacterSecurityLock,
                errorMessage,
                tietCreateCharacterSecurityLock
            )
        }
    }

    private fun showCharacterNameError(@StringRes errorMessage: Int) {
        binding.apply {
            showErrorInfo(tilCreateCharacterName, errorMessage, tietCreateCharacterName)
        }
    }

    private fun showSectError(@StringRes errorMessage: Int) {
        showErrorInfo(binding.tilCreateCharacterSect, errorMessage)
    }

    private fun showInternalError(@StringRes errorMessage: Int) {
        showErrorInfo(binding.tilCreateCharacterInternal, errorMessage)
    }

    private fun showErrorInfo(
        textInputLayout: TextInputLayout,
        @StringRes errorMessage: Int,
        textInputEditText: TextInputEditText? = null
    ) {
        textInputLayout.error = getString(errorMessage)
        textInputEditText?.requestFocus()
    }

    companion object {

        private val DIGITS_KEY_LISTENER =
            DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#\$%^&*()-_=+[{]}\\|;:'\",<.>/?")
    }

}