package com.springcassandra

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.ResultSet
import com.datastax.oss.driver.api.querybuilder.QueryBuilder
import com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal
import com.datastax.oss.driver.api.querybuilder.relation.Relation
import org.springframework.stereotype.Repository


@Repository
class MarvelRepository(
    val cqlSession: CqlSession
) {
    //CREATE
    fun saveHero(hero: Hero): ResultSet {

        val query = QueryBuilder.insertInto("heroes")
            .value("id", literal(hero.id))
            .value("nickname", literal(hero.nickname))
            .value("gender", literal(hero.gender))
            .value("race", literal(hero.race))
            .value("year_created", literal(hero.yearCr))
            .ifNotExists()
            .build()

        return cqlSession.execute(query)
    }

    //READ ALL
    fun getAllHeroes(): List<Hero> {
        val query = QueryBuilder.selectFrom("heroes")
            .all()
            .build()

        return cqlSession.execute(query)
            .map {
                Hero(
                    it.getInt("id"),
                    it.getString("nickname") ?: "no nickname",
                    it.getString("gender") ?: "no gender",
                    it.getString("race") ?: "no race",
                    it.getInt("year_created")
                )
            }.all()
    }

    //READ ONE
    fun getHeroById(heroId:Int): Hero? {
        val query = QueryBuilder.selectFrom("heroes")
            .all()
            .where(Relation.column("id").isEqualTo(literal(heroId)))
            .build()

        return cqlSession.execute(query)
            .map {
                Hero(
                    it.getInt("id"),
                    it.getString("nickname") ?: "no nickname",
                    it.getString("gender") ?: "no gender",
                    it.getString("race") ?: "no race",
                    it.getInt("year_created")
                )
            }.one()
    }

    //DELETE
    fun deleteHero(heroId: Int): ResultSet {
        val query = QueryBuilder.deleteFrom("heroes")
            .whereColumn("id").isEqualTo(literal(heroId))
            .build()

        return cqlSession.execute(query)
    }
}