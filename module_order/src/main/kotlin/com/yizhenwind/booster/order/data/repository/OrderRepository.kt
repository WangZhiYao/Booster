package com.yizhenwind.booster.order.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.data.database.dao.OrderDao
import com.yizhenwind.booster.data.database.mapper.OrderInfoToOrderMapper
import com.yizhenwind.booster.data.repository.IRepository
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * 订单 Repository
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao,
    private val orderInfoToOrderMapper: OrderInfoToOrderMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : IRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    fun observeOrderList(): Flow<PagingData<Order>> =
        Pager(pagingConfig) {
            orderDao.observeOrderList()
        }.flow
            .map { pagingData ->
                pagingData.map { orderInfoToOrderMapper.map(it) }
            }
            .flowOn(dispatcher)

    fun observeOrderListByCustomerId(customerId: Long): Flow<PagingData<Order>> =
        Pager(pagingConfig) {
            orderDao.observeOrderListByCustomerId(customerId)
        }.flow
            .map { pagingData ->
                pagingData.map { orderInfoToOrderMapper.map(it) }
            }
            .flowOn(dispatcher)

    fun observeOrderListByCharacterId(characterId: Long): Flow<PagingData<Order>> =
        Pager(pagingConfig) {
            orderDao.observeOrderListByCharacterId(characterId)
        }.flow
            .map { pagingData ->
                pagingData.map { orderInfoToOrderMapper.map(it) }
            }
            .flowOn(dispatcher)

}