package com.suv.iplknockout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suv.iplknockout.R
import kotlinx.android.synthetic.main.item_team_pairs.view.*
import java.lang.IndexOutOfBoundsException

class TeamsPairListAdapter(private val pairedList: List<List<String>>): RecyclerView.Adapter<TeamsPairListAdapter.TeamsPairListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsPairListViewHolder {
        return TeamsPairListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team_pairs, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TeamsPairListViewHolder, position: Int) {
        try {
            val groupTeamList = pairedList[position]
            holder.itemView.tv_team_one.text = groupTeamList[0]
            holder.itemView.tv_team_two.text = groupTeamList[1]
        } catch (exception: IndexOutOfBoundsException){
            exception.printStackTrace()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return pairedList.size
    }

    class TeamsPairListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }
    
}