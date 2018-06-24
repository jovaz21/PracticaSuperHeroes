package com.costular.marvelheroes.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Marvel Hero ROOM Database Entity
 */
@Entity(tableName = "heroes")
@Parcelize
data class MarvelHeroEntity(
        @PrimaryKey
        var name: String,
        val photoUrl: String,
        val realName: String,
        val height: String,
        val power: String,
        val abilities: String,
        val groups: String
): Parcelable