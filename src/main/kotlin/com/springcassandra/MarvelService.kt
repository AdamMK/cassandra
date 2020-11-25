package com.springcassandra

import com.springcassandra.utils.Failable
import com.springcassandra.utils.Failure
import com.springcassandra.utils.Success
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger { }

@Service
class MarvelService(
    val marvelRepository: MarvelRepository
) {
    //what if i try to override on the same id
    fun saveHero(hero: Hero) = marvelRepository.saveHero(hero)


    fun showAllHeroes() = marvelRepository.getAllHeroes()

    fun findHeroById(heroId: Int): Hero? = marvelRepository.getHeroById(heroId)

    fun deleteHeroById(heroId: Int): Boolean {
        val hero = findHeroById(heroId)
        return if (hero == null)
            false
        else
            marvelRepository.deleteHero(heroId).toList().isEmpty()
    }
}