package com.springcassandra.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

@Configuration
@EnableCassandraRepositories
class CassandraConfig : AbstractCassandraConfiguration() {


    @Value("\${spring.data.cassandra.keyspace-name}")
    lateinit var keyspace: String

    @Value("\${spring.data.cassandra.local-datacenter}")
    lateinit var localdc: String

    @Value("\${spring.data.cassandra.port}")
    var cassandraPort: Int? = null

    @Value("\${spring.data.cassandra.contact-points}")
    lateinit var cassandraContactPoints: String

    override fun getKeyspaceName(): String = keyspace
    override fun getLocalDataCenter(): String = localdc
    override fun getPort(): Int = cassandraPort ?: 9042
    override fun getContactPoints(): String = cassandraContactPoints

    override fun getKeyspaceCreations(): List<CreateKeyspaceSpecification> =
        listOf(
            CreateKeyspaceSpecification.createKeyspace(keyspace)
                .ifNotExists()
                .withSimpleReplication(1)
        )

    override fun getKeyspaceDrops(): List<DropKeyspaceSpecification> {
        return listOf(DropKeyspaceSpecification.dropKeyspace(keyspace))
    }
}