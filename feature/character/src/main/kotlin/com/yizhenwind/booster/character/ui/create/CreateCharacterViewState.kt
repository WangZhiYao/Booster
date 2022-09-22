package com.yizhenwind.booster.character.ui.create

import com.yizhenwind.booster.framework.base.IViewState
import com.yizhenwind.booster.model.*

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

    data class OnZoneSelected(val serverList: List<Server>) : CreateCharacterViewState()

    data class OnSectSelected(val internalList: List<Internal>) : CreateCharacterViewState()

    data class CreateCharacterSuccess(val character: Character) : CreateCharacterViewState()
}
