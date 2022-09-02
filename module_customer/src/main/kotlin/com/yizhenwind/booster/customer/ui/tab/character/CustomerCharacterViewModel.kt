package com.yizhenwind.booster.customer.ui.tab.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.mediator.character.ICharacterService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
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
) : BaseMVIViewModel<CustomerCharacterViewState, CustomerCharacterSideEffect>() {

    override val container: Container<CustomerCharacterViewState, CustomerCharacterSideEffect> =
        container(CustomerCharacterViewState.Init())

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