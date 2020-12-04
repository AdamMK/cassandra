package com.springcassandra

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.sql.ResultSet

@DisplayName("Marvel Service Tests")
@ExtendWith(MockKExtension::class)
class MarvelServiceTests {

    @MockK
    private lateinit var marvelRepository: MarvelRepository

    @InjectMockKs
    private lateinit var underTest: MarvelService


    @Nested
    inner class `Given a request to Heroes Service`{

        @Nested
        inner class `When all heros requested`{

            private val repoResponse = listOf(
                Hero(
                    id = 3,
                    nickname = "Wolverin",
                    gender = "male",
                    race = "human",
                    yearCr = 1960
                ),
                Hero(
                    id = 3,
                    nickname = "Superman",
                    gender = "male",
                    race = "cryptonian",
                    yearCr = 1940
                )
            )

            @Test
            fun `it will call repository and return all expected data`(){
                every { marvelRepository.getAllHeroes() } returns repoResponse

                val result = underTest.showAllHeroes()

                verify { marvelRepository.getAllHeroes() }
                assertEquals(repoResponse, result )
                assertEquals(2, result.size)
            }

        }

        @Nested
        inner class `When searching for Hero by Id with valid input`{

            private val repoResponse = Hero(
                    id = 3,
                    nickname = "Wolverin",
                    gender = "male",
                    race = "human",
                    yearCr = 1960
                )

            @Test
            fun `it will call repository and return all expected data`(){
                every { marvelRepository.getHeroById(any()) } returns repoResponse

                val heroId = 3
                val result = underTest.findHeroById(heroId)

                verify(exactly = 1) { marvelRepository.getHeroById(any()) }
                assertEquals(repoResponse, result)
            }
        }

        @Nested
        inner class `When searching for Hero by Id with invalid input` {

            @Test
            fun `it will call repository and return expected response`(){
                every { marvelRepository.getHeroById(any()) } returns null

                val result = underTest.findHeroById(heroId = 1)

                verify(exactly = 1) { marvelRepository.getHeroById(any()) }
                assertEquals(null, result)
            }
        }

//        @Nested
//        inner class `When saving new Hero with valid input` {
//            fun `it will call repository and return expected response`(){
//                every { marvelRepository.saveHero(any()) } returns ResultSet
//
//            }
//        }
    }


}