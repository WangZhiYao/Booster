package com.yizhenwind.booster.character.ui.create.server

import com.yizhenwind.booster.character.data.repository.ZoneServerRepository
import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.component.base.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/5
 */
@HiltViewModel
class ServerSelectionViewModel @Inject constructor(
    private val zoneServerRepository: ZoneServerRepository
) : BaseMVIViewModel<ServerSelectionViewState, ServerSelectionSideEffect>() {

    override val container =
        container<ServerSelectionViewState, ServerSelectionSideEffect>(ServerSelectionViewState())

    fun getServerListByZone(zone: Zone) {
        intent {
            zoneServerRepository.getServerByZone(zone)
                .collect { serverList ->
                    reduce {
                        state.copy(serverList = serverList)
                    }
                }
        }
    }
}