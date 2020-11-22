package com.springcassandra

import org.springframework.stereotype.Service

@Service
class MarvelService(
    val marvelRepository: MarvelRepository
) {

    fun saveHero(hero: Hero) = marvelRepository.saveHero(hero)

    fun showAllHeroes() = marvelRepository.getAllHeroes()

    fun showOneHero(heroId: Int): Hero? = marvelRepository.getHeroById(heroId)
}