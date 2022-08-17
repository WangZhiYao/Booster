package com.yizhenwind.booster.customer.ui.character

import androidx.paging.PagingData
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
sealed class CustomerCharacterViewState : IViewState {

    data class Init(val characterList: PagingData<Character> = PagingData.empty()) :
        CustomerCharacterViewState()

}
