package com.costular.marvelheroes.presentation.util

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.costular.marvelheroes.domain.model.MarvelHeroEntity

/**
 * Favorites Manager
 */
class FavoritesManager(context: Context) {
    val sharedPreferences: SharedPreferences
    val likeStatesMap: HashMap<String, MutableLiveData<Boolean>> = hashMapOf()
    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    // Like State
    fun likeState(hero: MarvelHeroEntity): MutableLiveData<Boolean> {
        var likeState = likeStatesMap.get(hero.name)
        if (likeState == null) { likeState = MutableLiveData()
            likeStatesMap.put(hero.name, likeState)
        }
        return(likeState)
    }

    // Set Liked
    fun setLiked(hero: MarvelHeroEntity?, isLiked: Boolean) {
        hero?.let {

            /* set */
            sharedPreferences.edit().putBoolean(it.name, isLiked).apply()

            /* set */
            var likeState = likeStatesMap.get(it.name)
            if (likeState == null) { likeState = MutableLiveData()
                likeStatesMap.put(it.name, likeState)
            }
            likeState.postValue(isLiked)
        }
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