package com.example.urbanapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.urbanapplication.di.ViewModelKey
import com.example.urbanapplication.di.factory.ViewModelFactory
import com.example.urbanapplication.viewmodel.MyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // Add viewmodels below
    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    internal abstract fun postMyViewModel(viewModel: MyViewModel): ViewModel

}