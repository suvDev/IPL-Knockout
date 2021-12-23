package com.suv.iplknockout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suv.iplknockout.IplApplication
import com.suv.iplknockout.R
import com.suv.iplknockout.adapters.StartIplListAdapter
import com.suv.iplknockout.di.ViewModelFactory
import com.suv.iplknockout.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class StartIplActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()

        btn_start.setOnClickListener {
            GameActivity.newInstance(this, Constants.teams)
        }
    }

    private fun initUi(){
        rv_teams.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_teams.adapter = StartIplListAdapter()
    }
}