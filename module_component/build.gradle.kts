plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    sourceSets {
        map {
            it.java.srcDir("src/${it.name}/kotlin")
        }
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    api(project(":module_common"))

    // Android X
    api("androidx.core:core-ktx:1.9.0")
    api("androidx.appcompat:appcompat:1.5.1")
    api("androidx.constraintlayout:constraintlayout:2.1.4")

    // Material
    api("com.google.android.material:material:1.6.1")

    // Lifecycle
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    api("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1")

    // Fragment
    api("androidx.fragment:fragment-ktx:1.5.2")

    // Navigation
    api("androidx.navigation:navigation-fragment-ktx:2.5.1")
    api("androidx.navigation:navigation-ui-ktx:2.5.1")

    // MVI
    api("org.orbit-mvi:orbit-viewmodel:4.3.2")

    // Paging3
    api("androidx.paging:paging-runtime-ktx:3.1.1")

    // Biometric
    api("androidx.biometric:biometric:1.2.0-alpha04")

}