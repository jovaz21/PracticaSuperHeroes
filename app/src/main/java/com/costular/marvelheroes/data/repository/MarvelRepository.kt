package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

/**
 * Marvel Repository
 */
class MarvelRepository(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource) {

    // Get Marvel Heroes from Remote DataSource:
    fun getMarvelHeroesFromRemoteDataSource(): Observable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource.getMarvelHeroesList()
}