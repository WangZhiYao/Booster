package com.yizhenwind.booster.data.network

import com.yizhenwind.booster.data.network.response.SectInternalListResponse
import com.yizhenwind.booster.data.network.response.ZoneServerListResponse
import retrofit2.http.GET

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