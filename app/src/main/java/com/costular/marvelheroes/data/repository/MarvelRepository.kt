package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

/**
 * Marvel Repository
 */
class MarvelRepository(private val localMarvelHeroesDataSource: LocalMarvelHeroesDataSource, private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource) {

    // Get Marvel Heroes from Local DataSource:
    fun getMarvelHeroesFromLocalDataSource(): Observable<List<MarvelHeroEntity>> =
            localMarvelHeroesDataSource.getMarvelHeroesList()

    // Get Marvel Heroes from Remote DataSource:
    fun getMarvelHeroesFromRemoteDataSource(): Observable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource.getMarvelHeroesList()
}