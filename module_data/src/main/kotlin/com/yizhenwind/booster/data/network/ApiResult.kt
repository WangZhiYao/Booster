package com.yizhenwind.booster.data.network

/**
 *
 * @author WangZhiYao
 * @since 2022/7/15
 */
sealed class ApiResult<out T>(status: ApiStatus, data: T? = null, exception: Exception? = null) {

    data class Loading<T>(
        val status: ApiStatus = ApiStatus.LOADING,
        val data: T? = null
    ) : ApiResult<T>(
        status = status,
        data = data
    )

    data class Success<out T>(
        val data: T?
    ) : ApiResult<T>(
        status = ApiStatus.SUCCESS,
        data = data,
        exception = null
    )

    data class Error<out T>(
        val exception: Exception?
    ) : ApiResult<T>(
        status = ApiStatus.ERROR,
        data = null,
        exception = exception
    )
}
