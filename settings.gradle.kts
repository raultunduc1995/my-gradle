pluginManagement {
    repositories {
        gradlePluginPortal()
        /* Own repository
           maven("https://my.location/repo") {
               credentials.apply {
                   username = "..."
                   password = "..."
               }
           }
       */
        includeBuild("gradle/plugins")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        /* Own repository
            maven("https://my.location/repo") {
                credentials.apply {
                    username = "..."
                    password = "..."
                }
            }
        */
        /* Other local gradle build
            includeBuild("../my-other-project")
         */
        includeBuild("gradle/platform")
    }
}
include(
    "app",
    "business-logic",
    "data-model"
)
rootProject.name = "My gradle project"