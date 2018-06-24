package com.costular.marvelheroes.data.mapper

import com.costular.marvelheroes.domain.model.MarvelHeroEntity

/**
 * Domain MarvelHeroEntity to Database MarvelHeroEntity
 */
class MarvelHeroDbEntityMapper : Mapper<MarvelHeroEntity, com.costular.marvelheroes.data.db.MarvelHeroEntity> {

    override fun transform(input: MarvelHeroEntity): com.costular.marvelheroes.data.db.MarvelHeroEntity =
            com.costular.marvelheroes.data.db.MarvelHeroEntity(
                    input.name,
                    input.photoUrl,
                    input.realName,
                    input.height,
                    input.power,
                    input.abilities,
                    getGroupsFromMarvelHeroEntity(input))

    override fun transformList(inputList: List<MarvelHeroEntity>): List<com.costular.marvelheroes.data.db.MarvelHeroEntity> =
            inputList.map { transform(it) }


    private fun getGroupsFromMarvelHeroEntity(marvelHeroEntity: MarvelHeroEntity): String =
            marvelHeroEntity.groups.joinToString(",")

}