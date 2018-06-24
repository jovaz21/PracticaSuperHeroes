package com.costular.marvelheroes.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.costular.marvelheroes.data.db.MarvelHeroDatabase
import com.costular.marvelheroes.data.mapper.MarvelHeroEntityMapper
import com.costular.marvelheroes.data.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelRepository
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by costular on 17/03/2018.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroMapper = MarvelHeroMapper()

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSource(marvelHeroesService: MarvelHeroesService,
                                            marvelHeroMapper: MarvelHeroMapper)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService, marvelHeroMapper)

    @Singleton
    @Provides
    fun provideMarvelHeroDatabase(context: Context): MarvelHeroDatabase =
            Room.databaseBuilder(context, MarvelHeroDatabase::class.java, "marvel.db").build()

    @Provides
    @Singleton
    fun provideMarvelHeroEntityMapper(): MarvelHeroEntityMapper = MarvelHeroEntityMapper()

    @Provides
    @Singleton
    fun provideLocalMarvelHeroesDataSource(marvelHeroDatabase: MarvelHeroDatabase,
                                            marvelHeroEntityMapper: MarvelHeroEntityMapper)
            : LocalMarvelHeroesDataSource =
            LocalMarvelHeroesDataSource(marvelHeroDatabase, marvelHeroEntityMapper)

    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(localMarvelHeroesDataSource: LocalMarvelHeroesDataSource,
            remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource): MarvelRepository =
            MarvelRepository(localMarvelHeroesDataSource, remoteMarvelHeroesDataSource)
}