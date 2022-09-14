pluginManagement {
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
include(":module_infra")
include(":module_component")
include(":module_common")
include(":module_data")
include(":module_mediator")
include(":module_main")
include(":module_customer")
include(":module_character")
include(":module_order")

includeBuild("./plugin")
include(":core:ui")
include(":core:common")
include(":core:model")
include(":core:data:database")
include(":core:data:network")
include(":core:data:datastore")
include(":core:mediator")

include(":feature:customer")
include(":feature:character")
include(":feature:zone")
include(":feature:server")
include(":feature:order")
include(":feature:category")
