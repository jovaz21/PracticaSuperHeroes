package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.db.MarvelHeroDatabase
import com.costular.marvelheroes.data.mapper.MarvelHeroEntityMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

/**
 * Local DataSource
 */
class LocalMarvelHeroesDataSource(private val database: MarvelHeroDatabase,
                                   private val marvelHeroesMapper: MarvelHeroEntityMapper): MarvelHeroesDataSource {
    override fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>> =
            database.getMarvelHeroDao().getAllHeroes()
                    .map { marvelHeroesMapper.transformList(it) }
                    .toObservable()
}