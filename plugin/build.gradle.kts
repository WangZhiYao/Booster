plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("version") {
            id = "com.yizhenwind.plugin.version"
            implementationClass = "com.yizhenwind.plugin.Version"
        }
        register("dependencies") {
            id = "com.yizhenwind.plugin.dependencies"
            implementationClass = "com.yizhenwind.plugin.Dependency"
        }
    }
}
