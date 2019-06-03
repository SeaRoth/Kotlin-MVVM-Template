package com.searoth.template.infrastructure.network

import com.searoth.template.domain.models.league.MatchSynopsis
import io.reactivex.Observable

interface MatchSynopsisDataSource {
    fun getMatches(accountId: String) : Observable<List<MatchSynopsis>>

    fun getMatchesFromDb(accountId: String) : Observable<List<MatchSynopsis>>

    fun getMatchesFromAPi(accountId: String) : Observable<List<MatchSynopsis>>

    fun insertMatches(list: List<MatchSynopsis>)
}