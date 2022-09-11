package com.yizhenwind.booster.character.ui.create.server

import com.yizhenwind.booster.common.model.Server
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/5
 */
data class ServerSelectionViewState(val serverList: List<Server> = emptyList()) : IViewState