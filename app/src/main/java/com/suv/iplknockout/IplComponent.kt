package com.suv.iplknockout

import com.suv.iplknockout.activities.GameActivity
import com.suv.iplknockout.activities.StartIplActivity
import com.suv.iplknockout.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface IplComponent {
    fun inject(startIplActivity: StartIplActivity)
    fun inject(gameActivity: GameActivity)
}