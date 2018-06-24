package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.costular.marvelheroes.di.components.DaggerGetMarvelHeroesListComponent
import com.costular.marvelheroes.di.modules.GetMarvelHeroesListModule
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.domain.usecase.GetMarvelHeroesList
import com.costular.marvelheroes.presentation.MainApp
import com.costular.marvelheroes.util.mvvm.BaseViewModel
import javax.inject.Inject

// TODO: Inject through Constructor
//class HeroesListViewModel @Inject constructor(private val getMarvelHeroesList: GetMarvelHeroesList): BaseViewModel() {
class HeroesListViewModel: BaseViewModel() {

    val marvelHeroesState: MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState: MutableLiveData<Boolean> = MutableLiveData()

    // TODO: Inject through Constructor (remove this)------
    @Inject
    lateinit var getMarvelHeroesList: GetMarvelHeroesList

    var setupDone = false
    fun setup(activity: HeroesListActivity) {
        if (setupDone)
            return
        DaggerGetMarvelHeroesListComponent.builder()
                .applicationComponent((activity.application as MainApp).component)
                .getMarvelHeroesListModule(GetMarvelHeroesListModule(activity))
                .build()
                .inject(this)
        setupDone = true
    }
    //TODO: --------------------------------

    fun loadMarvelHeroes() {
        isLoadingState.postValue(true)
        getMarvelHeroesList.execute({ heroes ->
            marvelHeroesState.value = heroes
            isLoadingState.postValue(false)
        }, {
            Log.d("HeroesViewModel", it.toString())
            isLoadingState.postValue(false)
        })
    }

}