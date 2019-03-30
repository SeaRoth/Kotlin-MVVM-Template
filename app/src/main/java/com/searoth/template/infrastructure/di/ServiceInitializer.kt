package com.searoth.template.infrastructure.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import com.searoth.template.R
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceInitializer {
    companion object {
        fun initServices(application: Application) {
            initApplication(application)
            initNetwork(application)
        }

        private fun initNetwork(application: Context) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://randastat.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            SeaRothServiceLocator.put(Retrofit::class.java, retrofit)

            application.let {
                val picasso = Picasso.Builder(it)
                    .build()
                SeaRothServiceLocator.put(Picasso::class.java, picasso)
            }
        }

        private fun initApplication(application: Application) {
            SeaRothServiceLocator.put(Application::class.java, application)
        }
    }
}
