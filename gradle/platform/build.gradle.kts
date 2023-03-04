plugins {
    `java-platform`
}
group = "com.example.platform"
javaPlatform.allowDependencies()
dependencies {
    // Here you can declare other dependency-constraints declared in other java-platforms
    api(platform("com.fasterxml.jackson:jackson-bom:2.13.3"))
    api(platform("org.junit:junit-bom:5.8.2"))
}
dependencies.constraints {
    api("org.apache.commons:commons-lang3:3.12.0")
    api("org.slf4j:slf4j-api:1.7.36")
    api("org.slf4j:slf4j-simple:1.7.36")
    /* Force gradle to import a chosen version of transitive dependency
        api("org:name:version") {
            version {
                strictly("acceptedVersion")
                reject("rejectedVersion")
            }
        }
     */
}