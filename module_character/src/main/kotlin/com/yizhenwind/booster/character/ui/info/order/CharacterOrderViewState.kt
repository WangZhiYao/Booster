package com.yizhenwind.booster.character.ui.info.order

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
sealed class CharacterOrderViewState : IViewState {

    data class GetCharacterOrderListSuccess(
        val characterOrderList: PagingData<Order> = PagingData.empty()
    ) : CharacterOrderViewState()

}