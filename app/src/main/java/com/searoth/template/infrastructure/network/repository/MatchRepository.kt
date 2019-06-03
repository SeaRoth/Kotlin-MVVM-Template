package com.searoth.template.infrastructure.network.repository

import com.searoth.template.domain.models.data.LeagueApiService
import com.searoth.template.domain.models.league.Match
import com.searoth.template.infrastructure.common.helpers.UrlHelper
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.searoth.template.infrastructure.network.MatchDataSource
import com.searoth.template.infrastructure.network.repository.local.MatchDao
import io.reactivex.Observable

class MatchRepository(val matchDao: MatchDao) : MatchDataSource {

    private var apiService : LeagueApiService = SeaRothServiceLocator.resolve(LeagueApiService::class.java)

    override fun getMatchesByNameFromDb(name: String): Observable<List<Match>> {
        return matchDao.getMatches().toObservable()
    }

    override fun getMatchesByIdFromDb(id: String): Observable<List<Match>> {
        return matchDao.getMatches().toObservable()
    }

    override fun getAllMatchesFromDb(): Observable<List<Match>> {
        return matchDao.getMatches().toObservable()
    }

    override fun getMatchFromApi(id: String): Observable<Match> {
        return apiService.getMatch(UrlHelper.buildMatch(id)).doOnNext { insertMatch(it) }
    }

    override fun insertMatch(match: Match) {matchDao.insertMatch(match)}

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