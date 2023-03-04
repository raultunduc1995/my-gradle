plugins {
    id("my-kotlin-application")
}
application {
    mainClass.set("com.example.myapp.MyApplicationKt")
}
dependencies {
    implementation(platform("com.example.platform:platform"))
    implementation(project(":data-model"))
    implementation(project(":business-logic"))
    // It provides an implementation for Logging api
    runtimeOnly("org.slf4j:slf4j-simple") // <=> implementation(libs.slf4j.simple)
}