package com.example.urbanapplication.di

import com.example.urbanapplication.MyApp
import com.example.urbanapplication.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MyApp>
}