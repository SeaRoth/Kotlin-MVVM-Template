package com.searoth.template.infrastructure.common.helpers

class UrlHelper {
    companion object{
        const val API_KEY = "RGAPI-bda13375-20f1-4350-b9b8-9784f1ca58f0"
        fun buildSummoner(name: String) : String {
            return "summoner/v4/summoners/by-name/$name?api_key=$API_KEY"
            //context.getString(R.string.summoner_by_name, name, context.getString(R.string.league_key))
        }

        //https://na1.api.riotgames.com/lol/match/v4/matchlists/by-account/oDhDY1mW8BPjbixtEW_dxIeu0ihIHzQQ4EG0JzTOKCAd-Q?api_key=RGAPI-bda13375-20f1-4350-b9b8-9784f1ca58f0
        fun buildMatchList(accountId: String) : String {
            return "matchlists/by-account/$accountId?api_key=$API_KEY"
        }

        //https://na1.api.riotgames.com/lol/match/v4/matches/3055960811?api_key=RGAPI-bda13375-20f1-4350-b9b8-9784f1ca58f0
        fun buildMatch(accountId: String) : String {
            return "match/v4/matches/$accountId?api_key=$API_KEY"
        }
    }
}