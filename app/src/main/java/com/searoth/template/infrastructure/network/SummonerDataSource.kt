package com.searoth.template.infrastructure.network

import com.searoth.template.domain.models.league.Summoner
import io.reactivex.Observable

interface SummonerDataSource {
    fun getSummoner(name: String) : Observable<Summoner>

    fun getSummonerFromDb(name: String) : Observable<Summoner>

    fun getSummonerFromApi(name: String) : Observable<Summoner>

    fun insertSummoner(summoner: Summoner)
}
