package com.suv.iplknockout.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suv.iplknockout.R
import kotlinx.android.synthetic.main.activity_winner.*

class WinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        initUI()
        btn_restart.setOnClickListener {
            finish()
        }
    }

    private fun initUI(){
        val teamList = intent?.getStringArrayListExtra(WINNERS_LIST)
        if(teamList!=null && teamList.isNotEmpty()){
            tv_winner_team.text = teamList[0]
            tv_runner_team.text = teamList[1]
        }
    }

    companion object{
        const val WINNERS_LIST = "WINNERS_LIST"

        fun newInstance(context: Context, teams: ArrayList<String>){
            val intent = Intent(context, WinnerActivity::class.java)
            intent.putStringArrayListExtra(WINNERS_LIST, teams)
            context.startActivity(intent)
        }
    }
}