package com.costular.marvelheroes.data.db

import android.arch.persistence.room.*
import io.reactivex.Maybe

/**
 * Marvel Hero DAO
 */
@Dao
abstract class MarvelHeroDao {

    @Query("SELECT * FROM heroes")
    abstract fun getAllHeroes(): Maybe<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(heroes: List<MarvelHeroEntity>)

    @Query("DELETE FROM heroes")
    abstract fun deleteAllHeroes()

    @Transaction
    open fun removeAndInsertUsers(heroes: List<MarvelHeroEntity>) {
        deleteAllHeroes()
        insertAll(heroes)
    }

}