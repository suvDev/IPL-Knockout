package com.suv.iplknockout.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suv.iplknockout.di.ViewModelFactory
import com.suv.iplknockout.di.annotation.ViewModelKey
import com.suv.iplknockout.viewmodels.GameActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GameActivityViewModel::class)
    abstract fun bindsGameActivityViewModel(startIplActivityViewModel: GameActivityViewModel) : ViewModel

}