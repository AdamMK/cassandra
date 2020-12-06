package com.springcassandra

import com.datastax.oss.driver.api.core.CqlSession
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@DisplayName("Marvel Repository Tests")
@ExtendWith(MockKExtension::class)
class MarvelRepositoryTests{

    @MockK
    private lateinit var cqlSession: CqlSession

    @InjectMockKs
    private lateinit var underTest: MarvelRepository

    @Nested
    inner class `Given a cql query` {

        @Nested
        inner class `When valid saveHero query is made`{


            @Test
            fun `then hero is being saved`{

            }
        }
    }

}