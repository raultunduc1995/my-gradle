plugins {
    id("com.autonomousapps.dependency-analysis")
}
tasks.register<Delete>("clean") {
    delete(buildDir)
}