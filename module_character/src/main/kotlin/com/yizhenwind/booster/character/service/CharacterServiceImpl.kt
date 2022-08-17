package com.yizhenwind.booster.character.service

import android.content.Context
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.character.data.domain.ObserveCharacterListByCustomerIdUseCase
import com.yizhenwind.booster.character.ui.create.CreateCharacterLaunchArgs
import com.yizhenwind.booster.character.ui.detail.CharacterDetailActivity
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.mediator.character.ICharacterService
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
        customer: Customer?,
        openDetailAfterCreateSuccess: Boolean
    ) {
        CreateCharacterLaunchArgs(customer, openDetailAfterCreateSuccess)
            .launch(context)
    }

    override fun launchCharacterDetail(context: Context, character: Character) {
        CharacterDetailActivity.start(context, character)
    }

    override fun observeCharacterListByCustomerId(customerId: Long): Flow<PagingData<Character>> =
        observeCharacterListByCustomerIdUseCase(customerId)

}