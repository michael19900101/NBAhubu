package com.aotuman.nbahubu.data.repository.player

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aotuman.nbahubu.data.entity.player.Player
import com.aotuman.nbahubu.data.entity.player.PlayerEntity
import com.aotuman.nbahubu.data.entity.player.PlayerInfoEntity
import com.aotuman.nbahubu.data.local.AppDataBase
import com.aotuman.nbahubu.data.mapper.Entity2ItemModelMapper
import com.aotuman.nbahubu.data.mapper.InfoEntity2InfoModelMapper
import com.aotuman.nbahubu.data.remote.player.PlayerResult
import com.aotuman.nbahubu.data.remote.player.PlayerService
import com.aotuman.nbahubu.model.player.PlayerInfoModel
import com.aotuman.nbahubu.model.player.PlayerItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

class PlayerPlayerRepositoryImpl(
    val api: PlayerService,
    val db: AppDataBase,
    val pageConfig: PagingConfig,
    val mapper2ItemMolde: Entity2ItemModelMapper,
    val mapper2InfoModel: InfoEntity2InfoModelMapper
) : PlayerRepository {

    override fun fetchPlayerList1(): Flow<List<Player>> {
        return flow {
            val count = db.playerDao().countPlayer()
            var players: List<Player> = mutableListOf();
            // 查询数据库是否存在
            if (count > 0) {
                players = db.playerDao().getPlayers().map {
                    Player(
                        id = it.id,
                        cnName = it.cnName,
                        enName = it.enName,
                        capital = it.capital,
                        teamId = it.teamId,
                        teamName = it.teamName,
                        teamLogo = it.teamLogo,
                        teamUrl = it.teamUrl,
                        jerseyNum = it.jerseyNum,
                        position = it.position,
                        birthStateCountry = it.birthStateCountry,
                        birth = it.birth,
                        height = it.height,
                        weight = it.weight,
                        icon = it.icon,
                        detailUrl = it.detailUrl,
                        wage = it.wage
                    )
                }
            } else {
                // 网络查找
                val playerResponse = api.fetchPlayerList1()
                val playerEntities = playerResponse.data?.map {
                    PlayerEntity(
                        id = it.id,
                        cnName = it.cnName,
                        enName = it.enName,
                        capital = it.capital,
                        teamId = it.teamId,
                        teamName = it.teamName,
                        teamLogo = it.teamLogo,
                        teamUrl = it.teamUrl,
                        jerseyNum = it.jerseyNum,
                        position = it.position,
                        birthStateCountry = it.birthStateCountry,
                        birth = it.birth,
                        height = it.height,
                        weight = it.weight,
                        icon = it.icon,
                        detailUrl = it.detailUrl,
                        wage = it.wage
                    )
                }
                // 插入本地数据库
                playerEntities?.let {
                    db.playerDao().insertPlayer(playerEntities)
                }
                playerResponse.data?.let {
                    players = it
                }
            }
            // 发射转换后的数据
            emit(players)
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchPlayerList(): Flow<PagingData<PlayerItemModel>> {
        return Pager(
            config = pageConfig,
            remoteMediator = PlayerRemoteMediator(api, db)
        ) {
            db.playerDao().getPlayer()
        }.flow.map { pagingData ->
            pagingData.map { mapper2ItemMolde.map(it) }
        }
    }

    override suspend fun fetchPlayerInfo(name: String): Flow<PlayerResult<PlayerInfoModel>> {
        return flow {
            try {
                val pokemonDao = db.playerInfoDao()
                // 查询数据库是否存在，如果不存在请求网络
                var infoModel = pokemonDao.getPokemon(name)
                if (infoModel == null) {
                    // 网络请求
                    val netWorkPokemonInfo = api.fetchPokemonInfo(name)

                    // 将网路请求的数据，换转成的数据库的 model，之后插入数据库
                    infoModel = PlayerInfoEntity.convert2PokemonInfoEntity(netWorkPokemonInfo)
                    // 插入更新数据库
                    pokemonDao.insertPokemon(infoModel)
                }
                // 将数据源的 model 转换成上层用到的 model，
                // ui 不能直接持有数据源，防止数据源的变化，影响上层的 ui
                val model = mapper2InfoModel.map(infoModel)

                // 发射转换后的数据
                emit(PlayerResult.Success(model))
            } catch (e: Exception) {
                emit(PlayerResult.Failure(e.cause))
            }
        }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程
    }

    override suspend fun fetchPlayerByParameter(parameter: String): Flow<PagingData<PlayerItemModel>> {
        return Pager(pageConfig) {
            // 加载数据库的数据
            db.playerDao().playerInfoByParameter(parameter)
        }.flow.map { pagingData ->

            // 数据映射，数据库实体 PersonEntity ——>  上层用到的实体 Person
            pagingData.map { mapper2ItemMolde.map(it) }
        }
    }

    companion object {
        private val TAG = "PokemonRepositoryImpl"
    }
}