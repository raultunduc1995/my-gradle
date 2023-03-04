plugins {
    `kotlin-dsl`
//    id("groovy-gradle-plugin") -> for groovy dsl
//    id("java-gradle-plugin") -> to implement plugins using only Java classes
}
// Declare plugins implemented in Java/Kotlin
//        gradlePlugin {
//            plugins.create("MyKotlinBasePlugin") {
//                id = "my-kotlin-application"
//                implementationClass = "com.example.gradle.MyKotlinApplicationClass"
//            }
//        }

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:1.19.0")
}