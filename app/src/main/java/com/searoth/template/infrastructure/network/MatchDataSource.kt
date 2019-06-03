package com.searoth.template.infrastructure.network

import com.searoth.template.domain.models.league.Match
import io.reactivex.Observable

interface MatchDataSource {
    fun getMatchesByNameFromDb(name: String) : Observable<List<Match>>

    fun getMatchesByIdFromDb(id: String) : Observable<List<Match>>

    fun getAllMatchesFromDb() : Observable<List<Match>>

    fun getMatchFromApi(id: String) : Observable<Match>

    fun insertMatch(match: Match)
}