package com.costular.marvelheroes.presentation.heroeslist

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.costular.marvelheroes.data.repository.MarvelRepository
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.FavoritesManager
import com.costular.marvelheroes.presentation.util.Navigator
import com.costular.marvelheroes.util.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(val navigator: Navigator, val favoritesManager: FavoritesManager, val marvelRepository: MarvelRepository): BaseViewModel() {
    val marvelHeroesState: MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState: MutableLiveData<Boolean> = MutableLiveData()

    // Load Marvel Heroes
    fun loadMarvelHeroes() {
        marvelRepository.getMarvelHeroes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoadingState.postValue(true) }
                .doOnTerminate { isLoadingState.postValue(false) }
                .subscribeBy(
                        onNext = {
                            marvelHeroesState.value = it
                        },
                        onError = {
                            Log.d("HeroesListViewModel", it.toString())
                        }
                )
                .addTo(compositeDisposable)
    }

    // On Marvel Hero Selected
    fun onMarvelHeroSelected(activity: Activity, hero: MarvelHeroEntity, image: View) {
        navigator.goToHeroDetail(activity, hero, image)
    }

    // On Like Updated
    fun onLikeUpdated(hero: MarvelHeroEntity, value: Boolean) {
        favoritesManager.setLiked(hero, value)
    }
}