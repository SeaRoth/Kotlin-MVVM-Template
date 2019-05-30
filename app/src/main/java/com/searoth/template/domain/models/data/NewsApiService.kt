package com.searoth.template.domain.models.data

import com.searoth.template.domain.models.BBCTopHeadline
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface NewsApiService {
    //https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=3e050ffdc2724f60b6e37bf5da3acab0
    @GET("top-headlines")
    fun getNews(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Observable<BBCTopHeadline>
}