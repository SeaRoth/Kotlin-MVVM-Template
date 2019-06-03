package com.searoth.template.infrastructure.common.helpers

class UrlHelper {
    companion object{
        const val API_KEY = "RGAPI-bda13375-20f1-4350-b9b8-9784f1ca58f0"
        fun buildSummoner(name: String) : String {
            return "summoner/v4/summoners/by-name/$name?api_key=$API_KEY"
            //context.getString(R.string.summoner_by_name, name, context.getString(R.string.league_key))
        }
    }
}