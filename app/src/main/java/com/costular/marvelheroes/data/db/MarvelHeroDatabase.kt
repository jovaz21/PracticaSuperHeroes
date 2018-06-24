package com.costular.marvelheroes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Marvel Heroes ROOM Database
 */
@Database(entities = [MarvelHeroEntity::class], version = 1)
abstract class MarvelHeroDatabase : RoomDatabase() {

    abstract fun getMarvelHeroDao(): MarvelHeroDao
}