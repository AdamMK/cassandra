package com.springcassandra.migration

import com.datastax.oss.driver.api.core.CqlSession
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.io.Resource
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class CQLMigration(
    val cqlSession: CqlSession,
    val resourceLoader: ResourcePatternResolver
) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val resources = resourceLoader.getResources("classpath:/cql/*.cql")
        logger.info { "Found ${resources.size} migrations to run." }
        try {
            processResources(resources)
        } catch (e: Exception) {
            logger.error { "Cannot load migrations - $e" }
        }
    }

    fun processResources(res: Array<Resource>) {
         res.flatMap { resource ->
            resource.inputStream.use {
                it.bufferedReader().readLines()
            }
         }.forEach { migration ->
            logger.info { migration }
            cqlSession.execute(migration)
         }
    }
}