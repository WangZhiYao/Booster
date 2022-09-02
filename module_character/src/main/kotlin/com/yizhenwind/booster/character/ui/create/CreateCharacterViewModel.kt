package com.yizhenwind.booster.character.ui.create

import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.data.domain.CreateCharacterUseCase
import com.yizhenwind.booster.character.data.domain.GetSectInternalListUseCase
import com.yizhenwind.booster.character.data.domain.GetZoneServerListUseCase
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.common.model.SectInternal
import com.yizhenwind.booster.common.model.ZoneServer
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.mediator.customer.ICustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import javax.inject.Inject

/**
 * 创建角色ViewModel
 *
 * @author WangZhiYao
 * @since 2022/4/23
 */
@HiltViewModel
class CreateCharacterViewModel @Inject constructor(
    private val customerService: ICustomerService,
    private val getZoneServerListUseCase: GetZoneServerListUseCase,
    private val getSectInternalListUseCase: GetSectInternalListUseCase,
    private val createCharacterUseCase: CreateCharacterUseCase,
) : BaseMVIViewModel<CreateCharacterViewState, CreateCharacterSideEffect>() {

    override val container =
        container<CreateCharacterViewState, CreateCharacterSideEffect>(CreateCharacterViewState.Init())

    private val zoneServerList: HashSet<ZoneServer> = HashSet()
    private val sectInternalList: HashSet<SectInternal> = HashSet()

    var customer: Customer? = null

    init {
        intent {
            getZoneServerListUseCase().combine(getSectInternalListUseCase()) { zoneServerList, sectInternalList ->
                CreateCharacterViewState.Init(
                    zoneServerList = zoneServerList,
                    sectInternalList = sectInternalList
                ).also {
                    this@CreateCharacterViewModel.zoneServerList.addAll(zoneServerList)
                    this@CreateCharacterViewModel.sectInternalList.addAll(sectInternalList)
                }
            }
                .combine(customerService.getCustomerList()) { state, customerList ->
                    state.copy(customerList = customerList)
                }
                .collectLatest {
                    reduce {
                        it
                    }
                }
        }
    }

    fun onZoneSelected(zoneName: String) {
        intent {
            reduce {
                CreateCharacterViewState.OnZoneSelected(
                    zoneServerList.first { it.zone.name == zoneName }.serverList
                )
            }
        }
    }

    fun onAccountChanged(account: String?) {
        intent {
            if (account.isNullOrEmpty()) {
                postSideEffect(CreateCharacterSideEffect.HideAccountError)
                return@intent
            }

            val isCustomAccount = account.matches(Constant.Regex.CUSTOM_ACCOUNT)
            val isEmail = account.matches(Constant.Regex.EMAIL)
            val isPhoneNumber = account.matches(Constant.Regex.PHONE_NUMBER)

            if (!isCustomAccount && !isEmail && !isPhoneNumber) {
                postSideEffect(CreateCharacterSideEffect.ShowAccountError(R.string.error_account_format))
                return@intent
            }

            postSideEffect(CreateCharacterSideEffect.HideAccountError)
        }
    }

    fun onPasswordChanged(password: String?) {
        intent {
            if (password.isNullOrEmpty()) {
                postSideEffect(CreateCharacterSideEffect.HidePasswordError)
                return@intent
            }

            if (!password.matches(Constant.Regex.PASSWORD)) {
                postSideEffect(CreateCharacterSideEffect.ShowPasswordError(R.string.error_password_format))
                return@intent
            }

            postSideEffect(CreateCharacterSideEffect.HidePasswordError)
        }
    }

    fun onSecurityLockChanged(securityLock: String?) {
        intent {
            if (securityLock.isNullOrEmpty()) {
                postSideEffect(CreateCharacterSideEffect.HideSecurityLockError)
                return@intent
            }

            if (securityLock.length < 6) {
                postSideEffect(CreateCharacterSideEffect.ShowSecurityLockError(R.string.error_security_lock))
                return@intent
            }

            postSideEffect(CreateCharacterSideEffect.HideSecurityLockError)
        }
    }

    fun onCharacterNameChanged(characterName: String?) {
        intent {
            if (characterName.isNullOrEmpty()) {
                postSideEffect(CreateCharacterSideEffect.HideCharacterNameError)
                return@intent
            }

            if (!characterName.matches(Constant.Regex.CHARACTER_NAME)) {
                postSideEffect(CreateCharacterSideEffect.ShowCharacterNameError(R.string.error_character_name))
                return@intent
            }

            postSideEffect(CreateCharacterSideEffect.HideCharacterNameError)
        }
    }

    fun onSectSelected(sectName: String) {
        intent {
            reduce {
                CreateCharacterViewState.OnSectSelected(
                    sectInternalList.first { it.sect.name == sectName }.internalList
                )
            }
        }
    }

    fun attemptCreateCharacter(
        customerId: Long?,
        zoneName: String,
        serverName: String,
        account: String?,
        password: String?,
        securityLock: String?,
        characterName: String?,
        sectName: String,
        internalName: String,
        remark: String?
    ) {
        intent {
            customerId ?: run {
                postSideEffect(CreateCharacterSideEffect.ShowCustomerError(R.string.error_customer))
                return@intent
            }

            val zoneServer = zoneServerList.find { it.zone.name == zoneName }
            zoneServer ?: run {
                postSideEffect(CreateCharacterSideEffect.ShowZoneError(R.string.error_zone))
                return@intent
            }

            val zone = zoneServer.zone
            val server = zoneServer.serverList.find { it.name == serverName }
            server ?: run {
                postSideEffect(CreateCharacterSideEffect.ShowServerError(R.string.error_server))
                return@intent
            }

            val isCustom = account?.matches(Constant.Regex.CUSTOM_ACCOUNT) ?: false
            val isEmail = account?.matches(Constant.Regex.EMAIL) ?: false
            val isPhoneNumber = account?.matches(Constant.Regex.PHONE_NUMBER) ?: false

            if (account.isNullOrBlank() || (!isCustom && !isEmail && !isPhoneNumber)) {
                postSideEffect(CreateCharacterSideEffect.ShowAccountError(R.string.error_account_format))
                return@intent
            }

            val passwordValid = password?.matches(Constant.Regex.PASSWORD) ?: false
            if (password.isNullOrEmpty() || !passwordValid) {
                postSideEffect(CreateCharacterSideEffect.ShowPasswordError(R.string.error_password_format))
                return@intent
            }

            if (!securityLock.isNullOrBlank() && securityLock.length < 6) {
                postSideEffect(CreateCharacterSideEffect.ShowSecurityLockError(R.string.error_security_lock))
                return@intent
            }

            val characterNameValid = characterName?.matches(Constant.Regex.CHARACTER_NAME) ?: false
            if (characterName.isNullOrBlank() || !characterNameValid) {
                postSideEffect(CreateCharacterSideEffect.ShowCharacterNameError(R.string.error_character_name))
                return@intent
            }

            val sectInternal = sectInternalList.find { it.sect.name == sectName }
            sectInternal ?: run {
                postSideEffect(CreateCharacterSideEffect.ShowSectError(R.string.error_sect))
                return@intent
            }

            val sect = sectInternal.sect
            val internal = sectInternal.internalList.find { it.name == internalName }
            internal ?: run {
                postSideEffect(CreateCharacterSideEffect.ShowInternalError(R.string.error_internal))
                return@intent
            }

            createCharacterUseCase(
                customerId,
                zone,
                server,
                account,
                password,
                securityLock,
                characterName,
                sect,
                internal,
                remark
            )
                .catch {
                    Timber.e(it)
                    postSideEffect(CreateCharacterSideEffect.CreateCharacterFailed(R.string.error_create_character))
                }
                .collect { character ->
                    character.ifNullOrElse({
                        postSideEffect(CreateCharacterSideEffect.CreateCharacterFailed(R.string.error_create_character))
                    }, {
                        reduce {
                            CreateCharacterViewState.CreateCharacterSuccess(it)
                        }
                    })
                }
        }
    }
}