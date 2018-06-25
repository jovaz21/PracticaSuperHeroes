package com.costular.marvelheroes.presentation.herodetail

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.FavoritesManager
import com.costular.marvelheroes.util.mvvm.BaseViewModel

/**
 * Hero Detail ViewModel
 */
class HeroDetailViewModel: BaseViewModel() {

    // TODO: FavoritesManager must be Dagger Injected
    lateinit var favoritesManager: FavoritesManager

    val likeState: MutableLiveData<Boolean> = MutableLiveData()

    // Setup
    // TODO: Remove Context once FavoritesManaged is Dagger Injected
    fun setUp(context: Context, hero: MarvelHeroEntity?) {
        favoritesManager = FavoritesManager(context)
        likeState.postValue(favoritesManager.isLiked(hero))
    }

    // On Like Updated
    fun onLikeUpdated(hero: MarvelHeroEntity?, value: Boolean) {
        favoritesManager.setLiked(hero, value)
        likeState.postValue(value)
    }
}