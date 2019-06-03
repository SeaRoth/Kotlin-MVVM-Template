package com.searoth.template.infrastructure.network.repository

import com.searoth.template.domain.models.data.LeagueApiService
import com.searoth.template.domain.models.league.MatchSynopsis
import com.searoth.template.infrastructure.common.helpers.UrlHelper
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.searoth.template.infrastructure.network.MatchSynopsisDataSource
import com.searoth.template.infrastructure.network.repository.local.MatchSynopsisDao
import io.reactivex.Observable

class MatchSynopsisSynopsisRepository(val matchSynopsisDao: MatchSynopsisDao) :
    MatchSynopsisDataSource {

    private var apiService : LeagueApiService = SeaRothServiceLocator.resolve(LeagueApiService::class.java)

    override fun getMatches(accountId: String): Observable<List<MatchSynopsis>> {
        return Observable.concatArrayEager(getMatchesFromDb(accountId), getMatchesFromAPi(accountId))
    }

    override fun getMatchesFromDb(accountId: String): Observable<List<MatchSynopsis>> {
        return matchSynopsisDao.getMatches()
            .toObservable()
            .doOnNext {  }
            .doOnError {  }
    }

    override fun getMatchesFromAPi(accountId: String): Observable<List<MatchSynopsis>> {
        return apiService.getMatchList(UrlHelper.buildMatchList(accountId))
            .doOnNext { insertMatches(it) }
    }

    override fun insertMatches(list: List<MatchSynopsis>) {
        matchSynopsisDao.insertMatches(list)
    }

    companion object {
        private var INSTANCE: MatchSynopsisSynopsisRepository? = null

        @JvmStatic fun getInstance(matchSynopsisDao: MatchSynopsisDao) : MatchSynopsisSynopsisRepository {
            return INSTANCE ?: MatchSynopsisSynopsisRepository(matchSynopsisDao).apply { INSTANCE = this }
        }

        @JvmStatic fun destroyInstance(){
            INSTANCE = null
        }
    }
}