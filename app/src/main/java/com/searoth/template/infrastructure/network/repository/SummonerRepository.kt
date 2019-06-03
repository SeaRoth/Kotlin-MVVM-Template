package com.searoth.template.infrastructure.network.repository

import android.content.Context
import com.searoth.template.domain.models.data.LeagueApiService
import com.searoth.template.domain.models.league.Summoner
import com.searoth.template.infrastructure.common.helpers.UrlHelper
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.searoth.template.infrastructure.network.SummonerDataSource
import com.searoth.template.infrastructure.network.repository.local.SummonerDao
import io.reactivex.Observable
import timber.log.Timber

class SummonerRepository(val summonerDao: SummonerDao) : SummonerDataSource {

    private var apiService : LeagueApiService = SeaRothServiceLocator.resolve(LeagueApiService::class.java)

    override fun getSummoner(name: String): Observable<Summoner> {
        return Observable.concatArrayEager(getSummonerFromDb(name), getSummonerFromApi(name))
    }

    override fun getSummonerFromDb(name: String): Observable<Summoner> {
        return summonerDao.getSummoner(name)
            .toObservable()
            .doOnNext {
                Timber.d("got summoner")
            }.doOnError{ Timber.d("error")}
    }

    override fun getSummonerFromApi(name: String): Observable<Summoner> {
        return apiService.getSummoner(UrlHelper.buildSummoner(name))
            .doOnNext {
                insertSummoner(it)
            }
            .doOnError { Timber.d("error") }
    }

    override fun insertSummoner(summoner: Summoner) {
        summonerDao.insertSummoner(summoner)
    }

    companion object {
        private var INSTANCE: SummonerRepository? = null

        @JvmStatic fun getInstance(summonerDao: SummonerDao) : SummonerRepository {
            return INSTANCE ?: SummonerRepository(summonerDao).apply { INSTANCE = this }
        }

        @JvmStatic fun destroyInstance(){
            INSTANCE = null
        }
    }
}