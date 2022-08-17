package com.yizhenwind.booster.data.network

import retrofit2.http.GET
import com.yizhenwind.booster.data.network.response.SectInternalListResponse
import com.yizhenwind.booster.data.network.response.ZoneServerListResponse

/**
 *
 * @author WangZhiYao
 * @since 2022/6/4
 */
interface BoosterService {

    @GET("zoneServerList")
    suspend fun getZoneServerList(): ZoneServerListResponse

    @GET("sectInternalList")
    suspend fun getSectInternalList(): SectInternalListResponse

}