package com.yizhenwind.booster.mediator.character

import android.content.Context
import androidx.paging.PagingData
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.mediator.IService
import com.yizhenwind.booster.model.Character
import kotlinx.coroutines.flow.Flow

/**
 * 角色模块对外接口
 *
 * @author WangZhiYao
 * @since 2022/7/14
 */
interface ICharacterService : IService {

    /**
     * 启动创建角色页面
     *
     * @param customerId                     可选，当前客户ID
     * @param openDetailAfterCreateSuccess 创建成功后是否打开角色详情页，默认为 false
     */
    fun launchCreateCharacter(
        context: Context,
        customerId: Long = Constant.DEFAULT_ID,
        openDetailAfterCreateSuccess: Boolean = false
    )

    /**
     * 启动角色信息页
     *
     * @param character 当前角色
     */
    fun launchCharacterTab(context: Context, character: Character)

    /**
     * 根据客户ID订阅角色列表
     *
     * @param customerId 客户ID
     */
    fun observeCharacterListByCustomerId(customerId: Long): Flow<PagingData<Character>>
}