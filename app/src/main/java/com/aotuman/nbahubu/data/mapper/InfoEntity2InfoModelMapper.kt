package com.aotuman.nbahubu.data.mapper

import com.aotuman.nbahubu.data.entity.player.PlayerInfoEntity
import com.aotuman.nbahubu.model.player.PlayerInfoModel

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
class InfoEntity2InfoModelMapper : Mapper<PlayerInfoEntity, PlayerInfoModel> {
    override fun map(input: PlayerInfoEntity): PlayerInfoModel {

        return convert2PokemonInfoEntity(input)
    }

    fun convert2PokemonInfoEntity(playerInfoModel: PlayerInfoEntity): PlayerInfoModel {
        return playerInfoModel.run {

//            val dbTypes = mutableListOf<PokemonInfoModel.Type>()
//            val dbStats = mutableListOf<PokemonInfoModel.Stats>()
//
//            types.forEach {
//                dbTypes.add(
//                    PokemonInfoModel.Type(
//                        name = it.name,
//                        url = it.url
//                    )
//                )
//            }
//
//            stats.forEach {
//                dbStats.add(
//                    PokemonInfoModel.Stats(
//                        baseStat = it.baseStat,
//                        name = it.name,
//                        url = it.url
//                    )
//                )
//            }
//
//            val albumsItem = mutableListOf<PokemonInfoModel.AlbumModel>()
//            if (!sprites.backDefault.isEmpty()) {
//                albumsItem.add(PokemonInfoModel.AlbumModel(index = 1, url = sprites.backDefault))
//            }
//            if (!sprites.backShiny.isEmpty()) {
//                albumsItem.add(PokemonInfoModel.AlbumModel(index = 3, url = sprites.backShiny))
//            }
//            if (!sprites.frontDefault.isEmpty()) {
//                albumsItem.add(PokemonInfoModel.AlbumModel(index = 5, url = sprites.frontDefault))
//            }
//            if (!sprites.frontShiny.isEmpty()) {
//                albumsItem.add(PokemonInfoModel.AlbumModel(index = 7, url = sprites.frontShiny))
//            }

            PlayerInfoModel(
//                name = name,
//                height = height,
//                weight = weight,
//                experience = experience,
//                types = dbTypes,
//                stats = dbStats,
//                albums = albumsItem
            )
        }
    }

}