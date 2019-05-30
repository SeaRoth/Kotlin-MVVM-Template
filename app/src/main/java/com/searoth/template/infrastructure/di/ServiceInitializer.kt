package com.searoth.template.infrastructure.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import com.searoth.template.R
import com.searoth.template.domain.models.data.NewsApiService
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceInitializer {
    companion object {
        fun initServices(application: Application) {
            initApplication(application)
            initNetwork(application)
        }

        private fun initNetwork(application: Context) {
            //https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=3e050ffdc2724f60b6e37bf5da3acab0
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            SeaRothServiceLocator.put(Retrofit::class.java, retrofit)

            val apiClient = retrofit.create(NewsApiService::class.java)
            SeaRothServiceLocator.put(NewsApiService::class.java, apiClient)

            application.let {
                val picasso = Picasso.Builder(it).build()
                SeaRothServiceLocator.put(Picasso::class.java, picasso)
            }
        }

        private fun initApplication(application: Application) {
            SeaRothServiceLocator.put(Application::class.java, application)
        }
    }
}
