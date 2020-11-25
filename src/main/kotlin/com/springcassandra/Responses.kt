package com.springcassandra


sealed class Responses

data class HeroSuccess(val hero: List<Hero>) : Responses()

data class HeroFailure(
    val error: Error,
    val message: String
): Responses()
