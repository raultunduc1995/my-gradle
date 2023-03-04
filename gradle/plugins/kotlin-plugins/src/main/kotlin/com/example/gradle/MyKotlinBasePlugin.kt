package com.example.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.register

class MyKotlinBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
//        plugins {
//            java
//            id("org.jetbrains.kotlin.jvm")
//        }
        target.plugins.apply {
            apply("java")
            apply("org.jetbrains.kotlin.jvm")
        }

//        sourceSets.main {}
//        sourceSets.test {}
        val sourceSetContainer =
            target.extensions.getByType(SourceSetContainer::class.java).apply {
                getByName("main") {}
                getByName("test") {}
            }
//
//        tasks.test {
//            // Force gradle to use JUnit 5 (BOM version) for test related tasks
//            useJUnitPlatform()
//            // Enable gradle to execute tests in parallel
//            maxParallelForks = 4
//            // Set the max memory heap size the tests can use
//            maxHeapSize = "2g"
//        }
        target.extensions.getByType(TaskContainer::class.java)
            .getByName("test", Test::class)
            .apply {
                useJUnitPlatform()
                maxParallelForks = 4
                maxHeapSize = "2g"
            }

//        val integrationTestSourceSet = sourceSets.create("integrationTest")
        val integrationTestSourceSet = sourceSetContainer.create("integrationTest")

//        val testIntegrationTestsTask = tasks.register<Test>("testIntegrationTests") {
//            // set the dirs where the task can find test-classes
//            testClassesDirs = integrationTestSourceSet.output.classesDirs
//            // set runtimeClasspath as default classpath
//            classpath = integrationTestSourceSet.runtimeClasspath
//            useJUnitPlatform()
//            maxParallelForks = 4
//            maxHeapSize = "2g"
//        }
        val testIntegrationTestsTask =
            target.tasks.register("tastIntegrationTests", Test::class) {
                testClassesDirs = integrationTestSourceSet.output.classesDirs
                classpath = integrationTestSourceSet.runtimeClasspath
                useJUnitPlatform()
                maxParallelForks = 4
                maxHeapSize = "2g"
            }

//        tasks.check {
//            dependsOn(testIntegrationTestsTask)
//        }
        target.tasks.getByName("check").dependsOn(testIntegrationTestsTask)


//        dependencies.components {
//            withModule<Slf4jSimpleRule>("org.slf4j:slf4j-simple")
//        }
        target.dependencies.components
            .withModule("org.slf4j:slf4j-simple", Slf4jSimpleRule::class.java)

//        java {
//            toolchain.languageVersion.set(JavaLanguageVersion.of(11))
//        }
        target.extensions.getByType(JavaPluginExtension::class.java)
            .toolchain
            .languageVersion
            .set(JavaLanguageVersion.of(11))

//        tasks.withType<JavaCompile>().configureEach {
//            options.encoding = "UTF-8"
//        }
        target.tasks.withType(JavaCompile::class.java)
            .configureEach {
                options.encoding = "UTF-8"
            }
    }
}