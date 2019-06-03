package com.searoth.template.domain.models.league

data class MatchListResponse(
    val endIndex: Int,
    val matches: List<MatchSynopsis>,
    val startIndex: Int,
    val totalGames: Int
)