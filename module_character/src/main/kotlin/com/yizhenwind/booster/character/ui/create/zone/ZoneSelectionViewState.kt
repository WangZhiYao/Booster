package com.yizhenwind.booster.character.ui.create.zone

import com.yizhenwind.booster.common.model.Zone
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
data class ZoneSelectionViewState(val zoneList: List<Zone> = emptyList()) : IViewState