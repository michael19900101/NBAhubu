package com.aotuman.nbahubu.data.mapper

import com.aotuman.nbahubu.data.entity.PlayerEntity
import com.aotuman.nbahubu.model.PlayerItemModel

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
class Entity2ItemModelMapper : Mapper<PlayerEntity, PlayerItemModel> {

    override fun map(input: PlayerEntity): PlayerItemModel =
            PlayerItemModel(cnName = input.name, teamUrl = input.url)

}