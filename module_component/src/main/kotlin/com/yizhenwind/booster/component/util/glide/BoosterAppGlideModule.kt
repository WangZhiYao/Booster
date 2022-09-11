package com.yizhenwind.booster.component.util.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/11
 */
@GlideModule
@Excludes(OkHttpLibraryGlideModule::class)
class BoosterAppGlideModule @Inject constructor(
    @ApplicationContext private val appContext: Context
) : AppGlideModule() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface BoosterAppGlideModuleEntryPoint {
        fun getOkHttpClient(): OkHttpClient
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(
                EntryPointAccessors.fromApplication(
                    appContext,
                    BoosterAppGlideModuleEntryPoint::class.java
                ).getOkHttpClient()
            )
        )
    }
}