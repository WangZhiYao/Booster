package com.yizhenwind.booster.character.ui.create.zone

import com.yizhenwind.booster.character.data.domain.GetZoneServerListUseCase
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
 * @since 2022/9/3
 */
@HiltViewModel
class ZoneSelectionViewModel @Inject constructor(
    private val getZoneServerListUseCase: GetZoneServerListUseCase
) : BaseMVIViewModel<ZoneSelectionViewState, ZoneSelectionSideEffect>() {

    override val container =
        container<ZoneSelectionViewState, ZoneSelectionSideEffect>(ZoneSelectionViewState())

    init {
        intent {
            getZoneServerListUseCase()
                .collect { zoneServerList ->
                    reduce {
                        state.copy(zoneList = zoneServerList.map { zoneServer -> zoneServer.zone })
                    }
                }
        }
    }
}