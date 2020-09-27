package com.springcassandra

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
class MarvelController(
    val marvelService: MarvelService
) {

    @PostMapping("/saveHero")
    fun saveHero(@RequestBody hero: Hero): ResponseEntity<Hero> {

        //Need to create self generating id
        marvelService.saveHero(hero)
        logger.info { "Saving $hero" }

        return ResponseEntity.ok(hero)
    }

    @GetMapping("/allHeroes")
    fun showAllHeros() : ResponseEntity<List<Hero>> {

        logger.info { "Fetching details of all Heroes" }

        return ResponseEntity.ok(marvelService.showAllHeroes())
    }


}