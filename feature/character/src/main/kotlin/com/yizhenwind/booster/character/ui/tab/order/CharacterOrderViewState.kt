package com.yizhenwind.booster.character.ui.tab.order

import androidx.paging.PagingData
import com.yizhenwind.booster.framework.base.IViewState
import com.yizhenwind.booster.model.Order

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