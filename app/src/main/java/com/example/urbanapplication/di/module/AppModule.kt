package com.example.urbanapplication.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.urbanapplication.BuildConfig
import com.example.urbanapplication.MyApp
import com.example.urbanapplication.repository.remote.UrbanService
import com.example.urbanapplication.repository.util.CalendarJsonAdapter
import com.example.urbanapplication.view.MainActivity
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class  AppModule {

    @Binds
    abstract fun bindApplication(app: MyApp): Application

    @ContributesAndroidInjector(modules = [NavHostModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSharedPreferences(
            app: Application
        ): SharedPreferences = app.getSharedPreferences("", Context.MODE_PRIVATE)

        @Provides
        @JvmStatic
        fun provideMoshi(): Moshi = Moshi.Builder().add(CalendarJsonAdapter).build()

        @Provides
        @JvmStatic
        fun providesOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            val clientBuilder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(httpLoggingInterceptor)
            }
            return clientBuilder.build()
        }

        @Provides
        @JvmStatic
        fun providesRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        @Provides
        @JvmStatic
        fun providesUrbanService(retrofit: Retrofit): UrbanService =
            retrofit.create(UrbanService::class.java)
    }
}