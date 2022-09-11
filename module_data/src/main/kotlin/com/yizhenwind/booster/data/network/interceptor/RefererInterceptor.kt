package com.yizhenwind.booster.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
class RefererInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.url.host.contains(HOST)) {
            request = request.newBuilder()
                .addHeader(HEADER_REFERER, HEADER_REFERER_YIZHENWIND)
                .build()
        }
        return chain.proceed(request)
    }

    companion object {

        private const val HOST = "zephaniel.site"

        private const val HEADER_REFERER = "Referer"

        private const val HEADER_REFERER_YIZHENWIND = "yizhenwind.com"
    }
}