package com.yizhenwind.booster.data.network.di

import com.squareup.moshi.Moshi
import com.yizhenwind.booster.data.network.BoosterService
import com.yizhenwind.booster.data.network.interceptor.RefererInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2022/6/4
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val DEFAULT_TIMEOUT = 15L

    private const val BASE_URL_BOOSTER = "https://api.zephaniel.site/booster/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(RefererInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideBoosterRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_BOOSTER)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideBoosterService(retrofit: Retrofit): BoosterService =
        retrofit.create(BoosterService::class.java)

}