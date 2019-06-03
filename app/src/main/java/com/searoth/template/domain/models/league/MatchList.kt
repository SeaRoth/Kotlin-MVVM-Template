package com.searoth.template.domain.models.league

data class MatchList(
    val endIndex: Int,
    val matches: List<Match>,
    val startIndex: Int,
    val totalGames: Int
)