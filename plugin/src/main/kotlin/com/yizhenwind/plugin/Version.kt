package com.yizhenwind.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/15
 */
class Version : Plugin<Project> {

    override fun apply(target: Project) {

    }

    companion object {

        const val compileSdk = 33

        const val minSdk = 21

        const val targetSdk = 33

        const val JUNIT = "4.13.2"

        const val ANDROID_TEST_JUNIT = "1.1.3"

        const val ANDROID_TEST_ESPRESSO = "3.4.0"

        const val KOTLIN = "1.6.4"

        const val CORE_KTX = "1.9.0"

        const val APPCOMPAT = "1.5.1"

        const val FRAGMENT = "1.5.2"

        const val NAVIGATION = "2.5.2"

        const val CONSTRAINT_LAYOUT = "2.1.4"

        const val PAGING = "3.1.1"

        const val LIFECYCLE = "2.5.1"

        const val ROOM = "2.4.3"

        const val DATASTORE = "1.0.0"

        const val MATERIAL = "1.6.1"

        const val HILT = "2.43.2"

        const val TIMBER = "5.0.1"

        const val ORBIT_MVI = "4.3.2"

        const val GLIDE = "4.13.2"

        const val OKHTTP = "5.0.0-alpha.9"

        const val RETROFIT = "2.9.0"

        const val MOSHI = "1.14.0"
    }
}