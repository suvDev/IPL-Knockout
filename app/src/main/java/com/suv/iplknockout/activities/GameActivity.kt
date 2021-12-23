package com.suv.iplknockout.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.suv.iplknockout.IplApplication
import com.suv.iplknockout.R
import com.suv.iplknockout.adapters.TeamsPairListAdapter
import com.suv.iplknockout.di.ViewModelFactory
import com.suv.iplknockout.viewmodels.GameActivityViewModel
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GameActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var gameActivityViewModel: GameActivityViewModel

    private var listOfTeams: ArrayList<String>? = null
    private var pairsOfTeams: ArrayList<ArrayList<String>>? = null
    private lateinit var teamsPairListAdapter: TeamsPairListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as IplApplication).iplComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameActivityViewModel = ViewModelProvider(this, factory)[GameActivityViewModel::class.java]

        initUi()
        btn_simulate.setOnClickListener {
            if (pairsOfTeams != null && pairsOfTeams!!.isNotEmpty()) {

                if (listOfTeams?.size == 2) {

                    // No need to again call getWinner function as we have already randomized this pair
                    WinnerActivity.newInstance(this, listOfTeams!!)
                    finish()
                } else {
                    setWinners()
                }
            }
        }
    }

    /* Choosing any random number between 0,1 and selecting it as winner for that pair,
    later making random pairs of the winners for further matches */
    private fun setWinners() {
        listOfTeams?.clear()
        for (team in pairsOfTeams!!) {
            val winner = gameActivityViewModel.getWinner()
            listOfTeams?.add(team[winner])
        }

        if (listOfTeams?.size == 2) {
            btn_simulate.text = getString(R.string.simulate_end)
        }

        listOfTeams?.let {
            pairsOfTeams?.clear()
            lifecycleScope.launch {
                val pairs = withContext(Dispatchers.IO){
                    gameActivityViewModel.getPairs(it)
                }
                pairs?.forEach {
                    pairsOfTeams?.add(it)
                }
                teamsPairListAdapter.notifyDataSetChanged()
            }
        }

    }

    private fun initUi() {
        try {
            listOfTeams = intent?.getStringArrayListExtra(LIST_TEAMS_WON)
            listOfTeams?.let {
                lifecycleScope.launch {

                    // generating pairs and initializing adapter
                    pairsOfTeams = withContext(Dispatchers.IO){
                        gameActivityViewModel.getPairs(it)
                    }
                    if (pairsOfTeams != null && pairsOfTeams!!.isNotEmpty()) {
                        rv_paired_teams.layoutManager =
                            LinearLayoutManager(this@GameActivity, LinearLayoutManager.VERTICAL, false)
                        teamsPairListAdapter = TeamsPairListAdapter(pairsOfTeams!!)
                        rv_paired_teams.adapter = teamsPairListAdapter
                    }
                }

            }
        } catch (castException: ClassCastException) {
            castException.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {

        private const val LIST_TEAMS_WON = "LIST_TEAMS_WON"

        fun newInstance(context: Context, teams: ArrayList<String>) {
            val intent = Intent(context, GameActivity::class.java)
            intent.putStringArrayListExtra(LIST_TEAMS_WON, teams)
            context.startActivity(intent)
        }

    }
}