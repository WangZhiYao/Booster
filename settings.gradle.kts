pluginManagement {
    includeBuild("./plugin")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Booster"
include(":app")

include(":core:framework")
include(":core:common")
include(":core:model")
include(":core:data:database")
include(":core:data:network")
include(":core:data:datastore")
include(":core:mediator")
include(":core:logger")

include(":feature:customer")
include(":feature:character")
include(":feature:zone")
include(":feature:server")
include(":feature:sect")
include(":feature:internal")
include(":feature:order")
include(":feature:category")
include(":feature:home")
