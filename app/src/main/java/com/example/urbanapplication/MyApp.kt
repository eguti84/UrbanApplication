package com.example.urbanapplication

import com.example.urbanapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out MyApp> =
        DaggerAppComponent.factory().create(this)
}