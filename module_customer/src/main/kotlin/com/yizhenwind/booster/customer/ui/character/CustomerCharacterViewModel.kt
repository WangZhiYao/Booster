package com.yizhenwind.booster.customer.ui.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.yizhenwind.booster.component.base.BaseViewModel
import com.yizhenwind.booster.mediator.character.ICharacterService
import javax.inject.Inject

/**
 * 客户角色 ViewModel
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
@HiltViewModel
class CustomerCharacterViewModel @Inject constructor(
    private val characterService: ICharacterService
) : ContainerHost<CustomerCharacterViewState, CustomerCharacterSideEffect>, BaseViewModel() {

    override val container =
        container<CustomerCharacterViewState, CustomerCharacterSideEffect>(
            CustomerCharacterViewState.Init()
        )

    fun observeCharactersByCustomerId(customerId: Long) {
        intent {
            characterService.observeCharacterListByCustomerId(customerId)
                .cachedIn(viewModelScope)
                .collect {
                    reduce {
                        CustomerCharacterViewState.Init(it)
                    }
                }
        }
    }
}