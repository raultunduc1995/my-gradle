package com.example.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files

// @CacheableTask - we avoid declaring this task cacheable because writing and reading from cache
//                  is more expensive than the task itself
abstract class JarCount : DefaultTask() {
    /*
        ConfigurableFileCollection - is used when you want to express that you want to add
                                     arbitrary files/folders to an input
                                   - is used with @InputFiles annotation
        RegularFileProperty - is used if you want a single file (@InputFile)
        DirectoryProperty   - is used if you want a single directory (@InputDirectory)
     */

    @get:InputFiles
    abstract val allJars: ConfigurableFileCollection

    @get:OutputFile
    abstract val countFile: RegularFileProperty

    @TaskAction
    fun doCount() {
        Files.write(
            countFile.get().asFile.toPath(),
            setOf("${allJars.files.size}")
        )
    }
}