package com.yizhenwind.booster.character.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yizhenwind.booster.common.Account
import com.yizhenwind.booster.common.Password
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import org.orbitmvi.orbit.viewmodel.container

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/4
 */
class CreateCharacterViewModel2 :
    BaseMVIViewModel<CreateCharacterViewState2, CreateCharacterSideEffect2>() {

    override val container =
        container<CreateCharacterViewState2, CreateCharacterSideEffect2>(CreateCharacterViewState2.Init)

    private val _customer: MutableLiveData<Customer> = MutableLiveData()
    val customer: LiveData<Customer> = _customer

    private val _zone: MutableLiveData<Zone> = MutableLiveData()
    val zone: LiveData<Zone> = _zone

    private val _server: MutableLiveData<Server> = MutableLiveData()
    val server: LiveData<Server> = _server

    private val _account: MutableLiveData<Account> = MutableLiveData()
    val account: LiveData<Account> = _account

    private val _password: MutableLiveData<Password> = MutableLiveData()
    val password: LiveData<Password> = _password

    fun setCustomer(customer: Customer) {
        _customer.value = customer
    }

    fun setZone(zone: Zone) {
        _zone.value = zone
    }

    fun setServer(server: Server) {
        _server.value = server
    }

    fun setAccount(account: Account) {
        _account.value = account
    }

    fun setPassword(password: Password) {
        _password.value = password
    }
}