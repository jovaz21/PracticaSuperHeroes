package com.costular.marvelheroes.presentation.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.costular.marvelheroes.domain.model.MarvelHeroEntity

/**
 * Favorites Manager
 */
class FavoritesManager(context: Context) {
    val sharedPreferences: SharedPreferences
    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    // Set Liked
    fun setLiked(hero: MarvelHeroEntity?, isLiked: Boolean) {
        hero?.let { sharedPreferences.edit().putBoolean(it.name, isLiked).apply() }
    }

    // Is Liked
    fun isLiked(hero: MarvelHeroEntity?): Boolean {

        /* check */
        if (hero == null)
            return(false)

        /* done */
        return(sharedPreferences.getBoolean(hero!!.name, false))
    }
}