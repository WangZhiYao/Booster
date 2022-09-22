import com.yizhenwind.plugin.Dependency
import com.yizhenwind.plugin.Version

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.yizhenwind.plugin.version")
    id("com.yizhenwind.plugin.dependencies")
}

android {
    compileSdk = Version.compileSdk

    defaultConfig {
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_TEST_JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_TEST_ESPRESSO_CORE)

    implementation(project(":core:common"))

    // Moshi
    implementation(Dependency.Library.MOSHI)
    kapt(Dependency.Library.MOSHI_KOTLIN_CODEGEN)

}