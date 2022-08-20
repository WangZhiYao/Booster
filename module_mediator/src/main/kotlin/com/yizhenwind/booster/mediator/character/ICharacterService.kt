package com.yizhenwind.booster.mediator.character

import android.content.Context
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.mediator.IService

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
     * @param customer                     可选，当前客户
     * @param openDetailAfterCreateSuccess 创建成功后是否打开角色详情页，默认为 false
     */
    fun launchCreateCharacter(
        context: Context,
        customer: Customer? = null,
        openDetailAfterCreateSuccess: Boolean = false
    )

    /**
     * 启动角色信息页
     *
     * @param character 当前角色
     */
    fun launchCharacterInfo(context: Context, character: Character)

    /**
     * 根据客户ID订阅角色列表
     *
     * @param customerId 客户ID
     */
    fun observeCharacterListByCustomerId(customerId: Long): Flow<PagingData<Character>>
}