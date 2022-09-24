package com.yizhenwind.booster.customer.data.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.booster.customer.data.CustomerRepository
import com.yizhenwind.booster.model.CustomerSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/24
 */
class ObserveCustomerSummaryListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) {

    operator fun invoke(): Flow<PagingData<CustomerSummary>> =
        customerRepository.observeCustomerSummaryList()
            .map { pagingData ->
                pagingData.map { customerSummary ->
                    customerSummary.run {
                        CustomerSummary(
                            id,
                            name,
                            contactType,
                            contact,
                            characterCount,
                            orderCount
                        )
                    }
                }
            }
}