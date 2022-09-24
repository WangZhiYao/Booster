package com.yizhenwind.booster.character.service

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.booster.character.data.domain.ObserveCharacterListByCustomerIdUseCase
import com.yizhenwind.booster.character.ui.create.CreateCharacterArgs
import com.yizhenwind.booster.character.ui.tab.CharacterTabArgs
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 角色模块对外接口实现类
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
class CharacterServiceImpl @Inject constructor(
    private val observeCharacterListByCustomerIdUseCase: ObserveCharacterListByCustomerIdUseCase
) : ICharacterService {

    override fun launchCreateCharacter(
        context: Context,
        customerId: Long,
        openDetailAfterCreateSuccess: Boolean
    ) {
        CreateCharacterArgs(customerId, openDetailAfterCreateSuccess).launch(context)
    }

    override fun launchCharacterTab(context: Context, character: Character) {
        CharacterTabArgs(character).launch(context)
    }

    override fun observeCharacterListByCustomerId(customerId: Long): Flow<PagingData<Character>> =
        observeCharacterListByCustomerIdUseCase(customerId)

}