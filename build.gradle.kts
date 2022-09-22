buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.0")
        classpath(kotlin("gradle-plugin", version = "1.7.10"))
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}