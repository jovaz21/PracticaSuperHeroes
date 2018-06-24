package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

/**
 * Marvel Heroes DataSource
 */
interface MarvelHeroesDataSource {

    // Get Marvel Heroes List
    fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>>
}