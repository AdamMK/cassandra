package com.springcassandra

import org.springframework.stereotype.Service

@Service
class MarvelService(
    val marvelRepository: MarvelRepository
) {

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