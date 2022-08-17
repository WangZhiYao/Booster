package com.yizhenwind.booster.order.data.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.data.domain.IUseCase
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/8
 */
class GetBillingCycleListUseCase @Inject constructor(
    @IODispatcher val ioDispatcher: CoroutineDispatcher
) : IUseCase {

    operator fun invoke(): Flow<List<BillingCycle>> =
        flow { emit(BillingCycle.values().toList()) }
            .flowOn(ioDispatcher)
}