package com.yizhenwind.booster.character.ui.detail

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
sealed class CharacterDetailViewState : IViewState {

    data class Init(val orders: PagingData<Order> = PagingData.empty()) : CharacterDetailViewState()
}