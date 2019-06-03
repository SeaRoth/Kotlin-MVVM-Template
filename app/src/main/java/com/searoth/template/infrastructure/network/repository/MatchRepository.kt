package com.searoth.template.infrastructure.network.repository

import com.searoth.template.domain.models.data.LeagueApiService
import com.searoth.template.domain.models.league.Match
import com.searoth.template.infrastructure.common.helpers.UrlHelper
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.searoth.template.infrastructure.network.repository.local.MatchDao
import io.reactivex.Observable

class MatchRepository(val matchDao: MatchDao) : MatchDataSource {

    private var apiService : LeagueApiService = SeaRothServiceLocator.resolve(LeagueApiService::class.java)

    override fun getMatches(accountId: String): Observable<List<Match>> {
        return Observable.concatArrayEager(getMatchesFromDb(accountId), getMatchesFromAPi(accountId))
    }

    override fun getMatchesFromDb(accountId: String): Observable<List<Match>> {
        return matchDao.getMatches()
            .toObservable()
            .doOnNext {  }
            .doOnError {  }
    }

    override fun getMatchesFromAPi(accountId: String): Observable<List<Match>> {
        return apiService.getMatchList(UrlHelper.buildMatchList(accountId))
            .doOnNext { insertMatches(it) }
    }

    override fun insertMatches(list: List<Match>) {
        matchDao.insertMatches(list)
    }

    companion object {
        private var INSTANCE: MatchRepository? = null

        @JvmStatic fun getInstance(matchDao: MatchDao) : MatchRepository {
            return INSTANCE ?: MatchRepository(matchDao).apply { INSTANCE = this }
        }

        @JvmStatic fun destroyInstance(){
            INSTANCE = null
        }
    }
}