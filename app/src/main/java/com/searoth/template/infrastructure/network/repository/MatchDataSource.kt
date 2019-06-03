package com.searoth.template.infrastructure.network.repository

import com.searoth.template.domain.models.league.Match
import io.reactivex.Observable

interface MatchDataSource {
    fun getMatches(accountId: String) : Observable<List<Match>>

    fun getMatchesFromDb(accountId: String) : Observable<List<Match>>

    fun getMatchesFromAPi(accountId: String) : Observable<List<Match>>

    fun insertMatches(list: List<Match>)
}