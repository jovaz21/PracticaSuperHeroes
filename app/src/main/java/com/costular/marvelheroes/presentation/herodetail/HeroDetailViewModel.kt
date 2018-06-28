package com.costular.marvelheroes.presentation.herodetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.FavoritesManager
import com.costular.marvelheroes.util.mvvm.BaseViewModel
import javax.inject.Inject

/**
 * Hero Detail ViewModel
 */
class HeroDetailViewModel @Inject constructor(val favoritesManager: FavoritesManager): BaseViewModel() {
    val likeState: MutableLiveData<Boolean> = MutableLiveData()

    // Setup
    fun setUp(hero: MarvelHeroEntity?) {
        likeState.postValue(favoritesManager.isLiked(hero))
    }

    // On Like Updated
    fun onLikeUpdated(hero: MarvelHeroEntity?, value: Boolean) {
        favoritesManager.setLiked(hero, value)
        likeState.postValue(value)
    }
}