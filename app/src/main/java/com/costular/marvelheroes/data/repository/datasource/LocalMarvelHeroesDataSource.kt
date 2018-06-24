package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.db.MarvelHeroDatabase
import com.costular.marvelheroes.data.mapper.MarvelHeroEntityMapper
import com.costular.marvelheroes.data.mapper.MarvelHeroDbEntityMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers

/**
 * Local DataSource
 */
class LocalMarvelHeroesDataSource(private val database: MarvelHeroDatabase,
                                   private val marvelHeroesMapper: MarvelHeroEntityMapper,
                                  private val marvelDbHeroesMapper: MarvelHeroDbEntityMapper): MarvelHeroesDataSource {

    // Get Marvel Heroes List
    override fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>> =
            database.getMarvelHeroDao().getAllHeroes()
                    .map { marvelHeroesMapper.transformList(it) }
                    .toObservable()

    // Save Marvel Heroes
    fun saveMarvelHeroes(heroes: List<MarvelHeroEntity>) {
        Observable.fromCallable {
            val dbHeroes = heroes.map { marvelDbHeroesMapper.transform(it) }
            database.getMarvelHeroDao().removeAndInsertHeroes(dbHeroes)
        }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }
}