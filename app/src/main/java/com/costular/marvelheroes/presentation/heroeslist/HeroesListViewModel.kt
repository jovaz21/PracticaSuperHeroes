package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.costular.marvelheroes.data.repository.MarvelRepository
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.util.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(val marvelRepository: MarvelRepository): BaseViewModel() {

    val marvelHeroesState: MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState: MutableLiveData<Boolean> = MutableLiveData()

    fun loadMarvelHeroes() {
        marvelRepository.getMarvelHeroesFromRemoteDataSource()
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
}