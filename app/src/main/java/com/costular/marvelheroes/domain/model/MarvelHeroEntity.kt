package com.costular.marvelheroes.domain.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Marvel Hero App Domain Entity
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class MarvelHeroEntity(
        val name: String,
        val photoUrl: String = "",
        val realName: String = "",
        val height: String = "",
        val power: String = "",
        val abilities: String = "",
        val groups: Array<String> = emptyArray()
) : Parcelable