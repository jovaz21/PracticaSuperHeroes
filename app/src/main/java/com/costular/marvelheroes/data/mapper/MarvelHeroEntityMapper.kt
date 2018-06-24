package com.costular.marvelheroes.data.mapper

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity

/**
 * Database MarvelHeroEntity to Domain MarvelHeroEntity
 */
class MarvelHeroEntityMapper : Mapper<com.costular.marvelheroes.data.db.MarvelHeroEntity, MarvelHeroEntity> {

    override fun transform(input: com.costular.marvelheroes.data.db.MarvelHeroEntity): MarvelHeroEntity =
            MarvelHeroEntity(
                    input.name,
                    input.photoUrl,
                    input.realName,
                    input.height,
                    input.power,
                    input.abilities,
                    getGroupsFromMarvelHeroEntity(input))

    override fun transformList(inputList: List<com.costular.marvelheroes.data.db.MarvelHeroEntity>): List<MarvelHeroEntity> =
            inputList.map { transform(it) }


    private fun getGroupsFromMarvelHeroEntity(marvelHeroEntity: com.costular.marvelheroes.data.db.MarvelHeroEntity): Array<String> =
            marvelHeroEntity.groups.replace("\\s".toRegex(), "").split(",").toTypedArray()

}