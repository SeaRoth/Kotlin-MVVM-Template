package com.searoth.template.infrastructure.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.searoth.template.domain.models.data.LeagueApiService

import com.searoth.template.domain.models.data.NewsApiService
import com.searoth.template.infrastructure.network.repository.MatchRepository
import com.searoth.template.infrastructure.network.repository.MatchSynopsisSynopsisRepository
import com.searoth.template.infrastructure.network.repository.SummonerRepository
import com.searoth.template.infrastructure.network.repository.local.TemplateDatabase
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceInitializer {
    companion object {
        fun initServices(application: Application) {
            initApplication(application)
            initNewsApiService(application)
            initLeagueAPiService(application)
            initRepositories(application)
        }

        private fun initNewsApiService(application: Context) {
            //https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=3e050ffdc2724f60b6e37bf5da3acab0
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            SeaRothServiceLocator.put(Retrofit::class.java, retrofit)

            val newsApiClient = retrofit.create(NewsApiService::class.java)
            SeaRothServiceLocator.put(NewsApiService::class.java, newsApiClient)

            application.let {
                val picasso = Picasso.Builder(it).build()
                SeaRothServiceLocator.put(Picasso::class.java, picasso)
            }
        }

        private fun initLeagueAPiService(application: Context){
            //https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/where%20yogradesat?api_key=RGAPI-6c264ff9-8ae4-43d4-87cf-2f0e9527d147
            val retrofit = Retrofit.Builder()
                .baseUrl("https://na1.api.riotgames.com/lol/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val leagueApiClient = retrofit.create(LeagueApiService::class.java)
            SeaRothServiceLocator.put(LeagueApiService::class.java, leagueApiClient)
        }

        private fun initApplication(application: Application) {
            SeaRothServiceLocator.put(Application::class.java, application)
        }

        private fun initRepositories(application: Context) {
            val database = TemplateDatabase.getInstance(application)
            SeaRothServiceLocator.put(SummonerRepository::class.java, SummonerRepository.getInstance(database.summonerDao()))
            SeaRothServiceLocator.put(MatchSynopsisSynopsisRepository::class.java, MatchSynopsisSynopsisRepository.getInstance(database.matchSynopsisDao()))
            SeaRothServiceLocator.put(MatchRepository::class.java, MatchRepository.getInstance(database.matchDao()))

        }
    }
}
