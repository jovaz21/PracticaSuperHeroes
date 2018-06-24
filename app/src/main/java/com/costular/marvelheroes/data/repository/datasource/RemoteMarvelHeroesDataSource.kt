package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

/**
 * Remote DataSource
 */
class RemoteMarvelHeroesDataSource(private val remoteService: MarvelHeroesService,
                                   private val marvelHeroesMapper: MarvelHeroMapper): MarvelHeroesDataSource {
    override fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>> =
            remoteService.getMarvelHeroesList()
                    .map { it.superheroes }
                    .map { marvelHeroesMapper.transformList(it) }
}