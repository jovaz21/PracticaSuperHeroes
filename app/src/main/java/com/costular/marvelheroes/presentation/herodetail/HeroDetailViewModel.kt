package com.costular.marvelheroes.presentation.herodetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.util.mvvm.BaseViewModel

class HeroDetailViewModel: BaseViewModel() {
    val likeState: MutableLiveData<Boolean> = MutableLiveData()

    // On Like Updated
    fun onLikeUpdated(value: Boolean) {
        // TODO: Update and Persist MarvelHeroEntity is (not) Liked
        likeState.postValue(value)
    }
}