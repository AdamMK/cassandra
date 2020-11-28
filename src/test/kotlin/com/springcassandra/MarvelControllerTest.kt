package com.springcassandra

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus

@DisplayName("Marvel Controller Tests")
@ExtendWith(MockKExtension::class)
class MarvelControllerTests {

    @MockK
    private lateinit var marvelService: MarvelService

    @InjectMockKs
    private lateinit var underTest: MarvelController


    @Nested
    inner class `Given a allHeroes endpoint valid request` {

        @Nested
        inner class `When data is retrieved from DB` {

            private val successResponse = listOf(Hero(
                id = 3,
                nickname ="Tralalam",
                gender = "male",
                race = "human",
                yearCr = 1982
            ))

            @Test
            fun `it will return 200 OK with expected payload`() {
                every{marvelService.showAllHeroes()} returns successResponse

                val result = underTest.showAllHeros()

                assertEquals(HttpStatus.OK, result.statusCode)
                assertEquals(successResponse, result.body)
            }
        }

        @Nested
        inner class `When data cannot be retrieved from DB` {

            private val emptyResponse = emptyList<Hero>()
            @Test
            fun `it will return not found error`() {
                every { marvelService.showAllHeroes() } returns emptyResponse

                val result = underTest.showAllHeros()

                assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
                assertEquals(emptyResponse, result.body)
            }
        }

    }


}