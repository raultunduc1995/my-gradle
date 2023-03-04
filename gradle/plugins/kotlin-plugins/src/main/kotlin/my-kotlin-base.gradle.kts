import com.example.gradle.Slf4jSimpleRule

plugins {
    java
    id("org.jetbrains.kotlin.jvm")
}

// Access the source-sets configs
sourceSets.main {
    // Set kotlin source-sets directory to sources
//    kotlin.setSrcDirs(listOf(layout.projectDirectory.dir("sources")))
}
sourceSets.test {}

// Setup test task for test source-set
tasks.test {
    // Force gradle to use JUnit 5 (BOM version) for test related tasks
    useJUnitPlatform()
    // Enable gradle to execute tests in parallel
    maxParallelForks = 4
    // Set the max memory heap size the tests can use
    maxHeapSize = "2g"
}

// To create new source-set
val integrationTestSourceSet = sourceSets.create("integrationTest")
// Setup test task for integrationTest source-set
val testIntegrationTestsTask = tasks.register<Test>("testIntegrationTests") {
    // set the dirs where the task can find test-classes
    testClassesDirs = integrationTestSourceSet.output.classesDirs
    // set runtimeClasspath as default classpath
    classpath = integrationTestSourceSet.runtimeClasspath
    useJUnitPlatform()
    maxParallelForks = 4
    maxHeapSize = "2g"
}
// Set a dependency to testIntegrationTestsTask
tasks.check {
    dependsOn(testIntegrationTestsTask)
}

// Add dependency-rules
dependencies.components
    .withModule<Slf4jSimpleRule>("org.slf4j:slf4j-simple")

// Set java version
java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

// This notation sets the encoding to UTF-8 on all tasks of JavaCompile type
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
/*
    tasks.compileJava {
        options.encoding = "UTF-8"
    }
    tasks.compileTestJava {
        options.encoding = "UTF-8"
    }
 */