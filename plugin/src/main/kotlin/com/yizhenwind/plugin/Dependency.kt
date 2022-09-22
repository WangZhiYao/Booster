package com.yizhenwind.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/15
 */
class Dependency : Plugin<Project> {


    override fun apply(target: Project) {

    }

    object Test {

        const val JUNIT = "junit:junit:${Version.JUNIT}"

        const val ANDROID_TEST_JUNIT = "androidx.test.ext:junit:${Version.ANDROID_TEST_JUNIT}"

        const val ANDROID_TEST_ESPRESSO_CORE =
            "androidx.test.espresso:espresso-core:${Version.ANDROID_TEST_ESPRESSO}"

        const val LEAKCANARY =
            "com.squareup.leakcanary:leakcanary-android:${Version.LEAKCANARY_ANDROID}"

    }

    object Kotlin {

        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.KOTLIN}"

        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.KOTLIN}"

    }

    object Androidx {

        const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"

        const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"

        const val FRAGMENT = "androidx.fragment:fragment-ktx:${Version.FRAGMENT}"

        const val NAVIGATION_FRAGMENT_KTX =
            "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"

        const val NAVIGATION_UI_KTX =
            "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"

        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"

        const val PAGING_RUNTIME_KTX = "androidx.paging:paging-runtime-ktx:${Version.PAGING}"

        const val LIFECYCLE_RUNTIME_KTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"

        const val LIFECYCLE_VIEW_MODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"

        const val STARTUP = "androidx.startup:startup-runtime:${Version.STARTUP}"

        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.ROOM}"

        const val ROOM_KTX = "androidx.room:room-ktx:${Version.ROOM}"

        const val ROOM_PAGING = "androidx.room:room-paging:${Version.ROOM}"

        const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM}"

        const val DATASTORE_PREFERENCES =
            "androidx.datastore:datastore-preferences:${Version.DATASTORE}"

    }

    object Google {

        const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"

        const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"

        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"

    }

    object Library {

        const val TIMBER = "com.jakewharton.timber:timber:${Version.TIMBER}"

        const val ORBIT_MVI = "org.orbit-mvi:orbit-viewmodel:${Version.ORBIT_MVI}"

        const val GLIDE = "com.github.bumptech.glide:glide:${Version.GLIDE}"

        const val GLIDE_OKHTTP3 = "com.github.bumptech.glide:okhttp3-integration:${Version.GLIDE}"

        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Version.GLIDE}"

        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Version.OKHTTP}"

        const val OKHTTP_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP}"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"

        const val RETROFIT_CONVERTER_MOSHI =
            "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT}"

        const val MOSHI = "com.squareup.moshi:moshi:${Version.MOSHI}"

        const val MOSHI_KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Version.MOSHI}"

    }
}