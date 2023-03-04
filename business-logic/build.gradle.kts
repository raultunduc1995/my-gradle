plugins {
    id("my-kotlin-lib")
}
dependencies {
    // Dependency-constraints
    implementation(platform("com.example.platform:platform"))
    implementation(project(":data-model"))

    implementation("org.apache.commons:commons-lang3") // <=> implementation(libs.commons.lang)
    // Only declares the slf4j api => an implementation is needed
    implementation("org.slf4j:slf4j-api") // <=> implementation(libs.slf4j.api)

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    integrationTestImplementation(platform("com.example.platform:platform"))
    integrationTestImplementation("org.junit.jupiter:junit-jupiter-api")
    integrationTestRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}