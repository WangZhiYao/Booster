package com.yizhenwind.booster.customer.data.domain

import com.yizhenwind.booster.common.constant.ContactType
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
class GetContactTypeListUseCase @Inject constructor(
    @IODispatcher val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<ContactType>> =
        flow { emit(ContactType.values().toList()) }
            .flowOn(ioDispatcher)
}