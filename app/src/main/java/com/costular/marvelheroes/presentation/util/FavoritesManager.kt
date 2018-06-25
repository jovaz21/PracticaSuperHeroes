package com.costular.marvelheroes.presentation.util

import com.costular.marvelheroes.domain.model.MarvelHeroEntity

/**
 * Favorites Manager
 */
object FavoritesManager {

    // Set Liked
    fun setLiked(hero: MarvelHeroEntity?, isLiked: Boolean) {
        // TODO: Set 'isLiked' within SharedPreferences
    }

    // Is Liked
    fun isLiked(hero: MarvelHeroEntity?): Boolean {
        // TODO: Check 'isLiked' within SharedPreferences
        return(false)
    }
}