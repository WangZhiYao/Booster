package com.yizhenwind.booster.character.ui.create

import com.yizhenwind.booster.common.model.*
import com.yizhenwind.booster.component.base.IViewState


/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/24
 */
sealed class CreateCharacterViewState : IViewState {

    data class Init(
        val customerList: List<Customer> = emptyList(),
        val zoneServerList: List<ZoneServer> = emptyList(),
        val sectInternalList: List<SectInternal> = emptyList()
    ) : CreateCharacterViewState()

    data class OnCustomerSelected(val customer: Customer) : CreateCharacterViewState()

    data class OnZoneSelected(val serverList: List<Server>) : CreateCharacterViewState()

    data class OnSectSelected(val internalList: List<Internal>) : CreateCharacterViewState()

    data class CreateCharacterSuccess(val character: Character) : CreateCharacterViewState()
}
