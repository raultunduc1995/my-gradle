package com.example.gradle

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

// To cache the result of the metadata-rule
@CacheableRule
class Slf4jSimpleRule : ComponentMetadataRule {
    override fun execute(context: ComponentMetadataContext) {
        context.details.allVariants {
            withDependencies {
                removeIf { directDependencyMetadata ->
                    // Here we exculde the slf4j-api dependency which comes with slf4j-simple
                    directDependencyMetadata.name.contains(
                        "slf4j-api",
                        ignoreCase = true
                    )
                }
            }
        }
    }
}
