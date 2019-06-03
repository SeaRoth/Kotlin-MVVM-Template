package com.searoth.template.domain.models.league

data class Match(
    val champion: Int,
    val gameId: Long,
    val lane: String,
    val platformId: String,
    val queue: Int,
    val role: String,
    val season: Int,
    val timestamp: Long
)