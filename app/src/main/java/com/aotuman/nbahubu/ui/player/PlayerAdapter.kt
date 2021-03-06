package com.aotuman.nbahubu.ui.player

import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aotuman.nbahubu.R
import com.aotuman.nbahubu.data.entity.player.Player


class PlayerAdapter(private val fragment: Fragment,
                    private val players: ArrayList<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mCnName: TextView

        val mEnName: TextView

        val mImgPlayer: ImageView

        val mImgTeamLogo: ImageView

        val mPlayPosition: TextView

        val mTeamName: TextView

        init {
            mCnName = view.findViewById(R.id.tv_cn_name)

            mEnName = view.findViewById(R.id.tv_en_name)

            mImgPlayer = view.findViewById(R.id.iv_player)

            mImgTeamLogo = view.findViewById(R.id.iv_team_logo)

            mPlayPosition = view.findViewById(R.id.tv_play_position)

            mTeamName = view.findViewById(R.id.tv_team_name)
        }

        fun bind(player: Player) {
            mCnName.text = player.cnName
            mEnName.text = player.enName
            mPlayPosition.text = player.position
            mTeamName.text = player.teamName
            mImgPlayer.load(player.icon){
                placeholder(R.mipmap.default_player)
            }
            mImgTeamLogo.load(player.teamLogo){
                placeholder(R.mipmap.default_teamlogo_90)
                error(R.mipmap.default_teamlogo_90)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_player_row, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
        holder.itemView.setOnClickListener{
            val intent = Intent()
            intent.setClass(it.context, PlayerDetailActivity::class.java)
            intent.putExtra("playerId", players[position].id)
            val options = ActivityOptions.makeSceneTransitionAnimation(fragment.activity, holder.mImgPlayer,
                PlayerDetailActivity.VIEW_NAME_HEADER_IMAGE)
            ActivityCompat.startActivity(it.context, intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int = players.size

    fun addData(list: List<Player>) {
        players.addAll(list)
    }
}