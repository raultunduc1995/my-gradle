import com.example.gradle.JarCount

plugins {
    application
    id("my-kotlin-base")
}
// Counts all jars and writes down the result in a text-file
val jarCountTask = tasks.register<JarCount>("countJars") {
    group = "my gradle"
    description = "Write the number of produced tasks in a text-file"
    // allJars - stores the input jars
    allJars.from(tasks.jar)
    allJars.from(configurations.runtimeClasspath)
    // countFile - stores output file path
    countFile.set(layout.buildDirectory.file("gen/count.txt"))
}
val createBundleTask = tasks.register<Zip>("createBundle") {
    group = "my gradle"
    description = "Create a zip file containing all the produced jars"
    // Stores the input data of the task
    from(tasks.jar)
    from(configurations.runtimeClasspath)
    // Set the output of jarCountProducer task as the input for the current task
    from(
        jarCountTask.flatMap { it.countFile }
            .map { it.asFile.path }
    )
    // Set the "distribution" folder as the destination for the resulting zip file.
    // This forlder is located inside "/build" directory.
    destinationDirectory.set(layout.buildDirectory.dir("distribution"))
}
tasks.build {
    dependsOn(createBundleTask)
}
// To add a lifecycle task, you just register it with no type at all
val buildAllLifecycleTask = tasks.register("buildAll") {
    group = "lifecycle"
    description = "Build the project and counts the produced jars"

    dependsOn(tasks.build)
    dependsOn(jarCountTask)
}