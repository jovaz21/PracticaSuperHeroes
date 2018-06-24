package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable

/**
 * Marvel Repository
 */
class MarvelRepository(private val localMarvelHeroesDataSource: LocalMarvelHeroesDataSource, private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource) {

    // Get Marvel Heroes:
    // - Always try to retrieve from remote DataSource
    // - If any heroes in local DataSource, load and use them
    // - The result is the CONCAT of both Observable Lists
    fun getMarvelHeroes(): Observable<List<MarvelHeroEntity>> {
        val res = getMarvelHeroesFromLocalDataSource().concatWith(getMarvelHeroesFromRemoteDataSource())
        return(res)
    }

    // Get Marvel Heroes from Local DataSource:
    private fun getMarvelHeroesFromLocalDataSource(): Observable<List<MarvelHeroEntity>> =
            localMarvelHeroesDataSource.getMarvelHeroesList()

    // Get Marvel Heroes from Remote DataSource:
    // - When heroes are correctly retrieved from the remote DataSource,
    // we store them into the local one
    // - Conflicted registers (already created) are replaced by new incoming data
    private fun getMarvelHeroesFromRemoteDataSource(): Observable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource.getMarvelHeroesList()
                    .doOnNext {
                        localMarvelHeroesDataSource.saveMarvelHeroes(it)
                    }
}