package com.yizhenwind.booster.customer.ui.tab.character

import androidx.paging.PagingData
import com.yizhenwind.booster.framework.base.IViewState
import com.yizhenwind.booster.model.Character

/**
 *
 * @author WangZhiYao
 * @since 2022/4/28
 */
sealed class CustomerCharacterViewState : IViewState {

    data class Init(val characterList: PagingData<Character> = PagingData.empty()) :
        CustomerCharacterViewState()

}
