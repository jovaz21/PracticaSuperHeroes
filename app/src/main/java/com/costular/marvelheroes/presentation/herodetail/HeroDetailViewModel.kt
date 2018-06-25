package com.costular.marvelheroes.presentation.herodetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.FavoritesManager
import com.costular.marvelheroes.util.mvvm.BaseViewModel

/**
 * Hero Detail ViewModel
 */
class HeroDetailViewModel: BaseViewModel() {
    val likeState: MutableLiveData<Boolean> = MutableLiveData()

    // Setup
    fun setUp(hero: MarvelHeroEntity?) {
        likeState.postValue(FavoritesManager.isLiked(hero))
    }

    // On Like Updated
    fun onLikeUpdated(hero: MarvelHeroEntity?, value: Boolean) {
        FavoritesManager.setLiked(hero, value)
        likeState.postValue(value)
    }
}