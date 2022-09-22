ext {
    versions = [
            "junit"             : "4.13.2",
            "androidx-ext-junit": "1.1.3",
            "espresso"          : "3.4.0",
            "leakcanary"        : "2.9.1",
            "kotlin-coroutines" : "1.6.4",
            "core-ktx"          : "1.9.0",
            "appcompat"         : "1.5.1",
            "fragment"          : "1.5.2",
            "navigation"        : "2.5.2",
            "constraint-layout" : "2.1.4",
            "paging"            : "3.1.1",
            "lifecycle"         : "2.5.1",
            "startup"           : "1.1.1",
            "room"              : "2.4.3",
            "datastore"         : "1.0.0",
            "material"          : "1.6.1",
            "hilt"              : "2.43.2",
            "timber"            : "5.0.1",
            "orbit-mvi"         : "4.3.2",
            "glide"             : "4.13.2",
            "okhttp"            : "5.0.0-alpha.9",
            "retrofit"          : "2.9.0",
            "moshi"             : "1.14.0"
    ]

    kotlin = [
            "coroutines-core"   :
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:$versions.kotlin-coroutines",
            "coroutines-android":
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:$versions.kotlin-coroutines"
    ]

    androidx = [
            "core-ktx"                : "androidx.core:core-ktx:$versions.core-ktx",
            "appcompat"               : "androidx.appcompat:appcompat:$versions.appcompat",
            "fragment"                : "androidx.fragment:fragment-ktx:$versions.fragment",
            "navigation-ui-ktx"       : "androidx.navigation:navigation-ui-ktx:$versions.navigation",
            "navigation-fragment-ktx" : "androidx.navigation:navigation-fragment-ktx:$versions.navigation",
            "constraint-layout"       : "androidx.constraintlayout:constraintlayout:$versions.constraint-layout",
            "paging-runtime-ktx"      : "androidx.paging:paging-runtime-ktx:$versions.paging",
            "lifecycle-runtime-ktx"   : "androidx.lifecycle:lifecycle-runtime-ktx:$versions.lifecycle",
            "lifecycle-view-model-ktx": "androidx.lifecycle:lifecycle-viewmodel-ktx:$versions.lifecycle",
            "startup"                 : "androidx.startup:startup-runtime:$versions.startup",
            "room-runtime"            : "androidx.room:room-runtime:$versions.room",
            "room-ktx"                : "androidx.room:room-ktx:$versions.room",
            "room-paging"             : "androidx.room:room-paging:$versions.room",
            "room-compiler"           : "androidx.room:room-compiler:$versions.room",
            "datastore-preferences"   : "androidx.datastore:datastore-preferences:$versions.datastore"
    ]

    google = [
            "material"     : "com.google.android.material:material:$versions.material",
            "hilt"         : "com.google.dagger:hilt-android:$versions.hilt",
            "hilt-compiler": "com.google.dagger:hilt-android-compiler:$versions.hilt"
    ]

    timber = "com.jakewharton.timber:timber:$versions.timber"

    orbit = "org.orbit-mvi:orbit-viewmodel:$versions.orbit-mvi"

    glide = [
            "glide"         : "com.github.bumptech.glide:glide:$versions.glide",
            "glide-okhttp3" : "com.github.bumptech.glide:okhttp3-integration:$versions.glide",
            "glide-compiler": "com.github.bumptech.glide:compiler:$versions.glide"
    ]

    okhttp = [
            "okhttp"                    : "com.squareup.okhttp3:okhttp:$versions.okhttp",
            "okhttp-logging-interceptor": "com.squareup.okhttp3:logging-interceptor:$versions.okhttp"
    ]

    retrofit = [
            "retrofit"                : "com.squareup.retrofit2:retrofit:$versions.retrofit",
            "retrofit-converter-moshi": "com.squareup.retrofit2:converter-moshi:$versions.retrofit"
    ]

    moshi = [
            "moshi"               : "com.squareup.moshi:moshi:$versions.moshi",
            "moshi-kotlin-codegen": "com.squareup.moshi:moshi-kotlin-codegen:$versions.moshi"
    ]
}