import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.yizhenwind.plugin.Dependency
import com.yizhenwind.plugin.Version
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.yizhenwind.plugin.version")
    id("com.yizhenwind.plugin.dependencies")
}

android {
    namespace = "com.yizhenwind.booster"

    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = "com.yizhenwind.booster"
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    applicationVariants.all {
        outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA)
                val outputFileName =
                    "Booster-$versionName-$name-${dateFormat.format(Date())}.${output.outputFile.extension}"
                output.outputFileName = outputFileName
            }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_TEST_JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_TEST_ESPRESSO_CORE)

    // LeakCanary
    debugImplementation(Dependency.Test.LEAKCANARY)

    implementation(project(":core:framework"))

    implementation(project(":feature:home"))
    implementation(project(":feature:customer"))
    implementation(project(":feature:character"))
    implementation(project(":feature:zone"))
    implementation(project(":feature:server"))
    implementation(project(":feature:sect"))
    implementation(project(":feature:internal"))
    implementation(project(":feature:order"))
    implementation(project(":feature:category"))

    // Hilt
    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)
}