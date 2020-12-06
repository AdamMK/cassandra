package com.springcassandra

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

private val logger = KotlinLogging.logger {}

@RestController
class MarvelController(
    val marvelService: MarvelService
) {

    @PostMapping("/saveHero")
    fun saveHero(@RequestBody hero: Hero): ResponseEntity<String> {


        val randomHero = Hero(
            //not idempotent - collisions?
            id = hero.id ?: Random.nextInt(0, 5000),
            nickname = hero.nickname ?: "unknown",
            gender = hero.gender ?: "unknown",
            race = hero.race ?: "unknown",
            yearCr = hero.yearCr ?: 0
        )

        marvelService.saveHero(randomHero)
        logger.info { "Saving $hero" }

        return ResponseEntity.ok("Hero Id ${randomHero.id} has been saved")
    }

    @GetMapping("/allHeroes")
    fun showAllHeros() : ResponseEntity<List<Hero>>{
        logger.info{"Fetching all heroes"}
        val response = marvelService.showAllHeroes()
        return if (response.isNotEmpty())
             ResponseEntity.ok(response)
        else ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyList())
        }


    @GetMapping("/getHero/{id}")
    fun showOneHero(@PathVariable("id") heroId: Int) : ResponseEntity<Hero> {
        val hero = marvelService.findHeroById(heroId)

        logger.info { "Fetching detail of hero id $heroId" }

        return if (hero != null) {
            ResponseEntity.ok(hero)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/deleteHero/{id}")
    fun deleteHero(@PathVariable("id") heroId: Int) : ResponseEntity<Boolean> {

        logger.info { "Request to delete hero record Id $heroId" }
        return ResponseEntity.ok(marvelService.deleteHeroById(heroId))
    }


}