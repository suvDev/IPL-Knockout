package com.suv.iplknockout

import android.app.Application

class IplApplication : Application(){

    lateinit var iplComponent: IplComponent

    override fun onCreate() {
        super.onCreate()
        iplComponent = DaggerIplComponent.builder().build()
    }

}