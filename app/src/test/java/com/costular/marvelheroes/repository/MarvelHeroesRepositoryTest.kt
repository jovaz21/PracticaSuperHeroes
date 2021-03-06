package com.costular.marvelheroes.repository

import com.costular.marvelheroes.data.repository.MarvelRepository
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import org.junit.Before
import org.junit.Test

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryTest {

    private val mockLocalDataSource: LocalMarvelHeroesDataSource = mock()
    private val mockRemoteDataSource: RemoteMarvelHeroesDataSource = mock()

    private lateinit var marvelRepository: MarvelRepository

    @Before
    fun setUp() {
        marvelRepository = MarvelRepository(mockLocalDataSource, mockRemoteDataSource)
    }

    @Test
    fun `repository should retrieve marvel heroes list`() {
        val heroes = listOf(MarvelHeroEntity("Iron Man"), MarvelHeroEntity("Spider-Man"))
        val observable = Observable.just(heroes)

        whenever(mockRemoteDataSource.getMarvelHeroesList()).thenReturn(observable)

        val result = marvelRepository.getMarvelHeroesFromRemoteDataSource()

        verify(mockRemoteDataSource).getMarvelHeroesList()
        result.test()
                .assertValue { it.size == 2 }
                .assertValue { it.first().name == heroes.first().name }
                .assertValue { it.last().name == heroes.last().name }
    }

}