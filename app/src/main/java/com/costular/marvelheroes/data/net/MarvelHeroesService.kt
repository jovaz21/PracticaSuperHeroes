package com.costular.marvelheroes.data.net

import com.costular.marvelheroes.data.model.MarvelHeroesResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Marvel Heroes Remote Service Definition (Retrofit Implemented => See Dagger NetModule)
 */
interface MarvelHeroesService {

    @GET(".")
    fun getMarvelHeroesList(): Observable<MarvelHeroesResponse>

}