package com.searoth.template.domain.models.data

import com.searoth.template.domain.models.league.Summoner
import io.reactivex.Observable
import retrofit2.http.*

interface LeagueApiService {

    //https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/where%20yogradesat?api_key=RGAPI-6c264ff9-8ae4-43d4-87cf-2f0e9527d147
    @GET("summoner/v4/summoners/by-name/")
    fun getSummoner(@Query("", encoded = false)name: String, @Query("api_key") apiKey: String): Observable<Summoner>

    @GET
    fun getSummoner(@Url url: String): Observable<Summoner>

}