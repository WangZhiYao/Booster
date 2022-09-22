package com.yizhenwind.booster.order.data.domain

import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.framework.di.coroutine.qualifier.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/8
 */
class GetBillingCycleListUseCase @Inject constructor(
    @IODispatcher val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<BillingCycle>> =
        flow { emit(BillingCycle.values().toList()) }
            .flowOn(ioDispatcher)
}