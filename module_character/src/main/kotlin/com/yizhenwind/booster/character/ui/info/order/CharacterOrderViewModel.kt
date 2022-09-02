package com.yizhenwind.booster.character.ui.info.order

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@HiltViewModel
class CharacterOrderViewModel @Inject constructor(
    private val orderService: IOrderService
) : BaseMVIViewModel<CharacterOrderViewState, CharacterOrderSideEffect>() {

    override val container: Container<CharacterOrderViewState, CharacterOrderSideEffect> =
        container(CharacterOrderViewState.GetCharacterOrderListSuccess())

    fun observeOrderListByCharacterId(characterId: Long) {
        intent {
            orderService.observeOrderListByCharacterId(characterId)
                .cachedIn(viewModelScope)
                .collect {
                    CharacterOrderViewState.GetCharacterOrderListSuccess(it)
                }
        }
    }
}