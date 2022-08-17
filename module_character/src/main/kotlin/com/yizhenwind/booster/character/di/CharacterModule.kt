package com.yizhenwind.booster.character.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.yizhenwind.booster.character.service.CharacterServiceImpl
import com.yizhenwind.booster.mediator.character.ICharacterService

/**
 * 角色模块对外接口注册
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CharacterModule {

    @Binds
    abstract fun bindCharacterService(
        characterServiceImpl: CharacterServiceImpl
    ): ICharacterService

}