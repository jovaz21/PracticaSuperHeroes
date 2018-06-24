package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

/**
 * Fake DataSource
 */
class FakeMarvelHeroesDataSource : MarvelHeroesDataSource {
    override fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>> {
        return Observable.just(
                arrayListOf(
                        MarvelHeroEntity("Fake", "https://i.blogs.es/30cb7a/blackpanther5/450_1000.jpg"),
                        MarvelHeroEntity("Fake 2", "https://thefreakchoice.com/2833-home_default/cojin-arrow-flechas-verde-.jpg")
                )
        )
    }
}