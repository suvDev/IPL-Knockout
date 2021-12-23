package com.suv.iplknockout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suv.iplknockout.R
import com.suv.iplknockout.utils.Constants
import kotlinx.android.synthetic.main.item_team_name.view.*
import java.lang.IndexOutOfBoundsException

class StartIplListAdapter
    : RecyclerView.Adapter<StartIplListAdapter.StartIplListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartIplListViewHolder {
        return StartIplListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team_name, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StartIplListViewHolder, position: Int) {
        try {
           holder.itemView.tv_team_name.text = Constants.teams[position]
        } catch (exception: IndexOutOfBoundsException){
            exception.printStackTrace()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return Constants.teams.size
    }

    class StartIplListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }

}