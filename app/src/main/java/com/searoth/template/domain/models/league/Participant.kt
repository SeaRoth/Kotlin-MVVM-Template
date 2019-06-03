package com.searoth.template.domain.models.league

data class Participant(
    val championId: Int,
    val highestAchievedSeasonTier: String,
    val participantId: Int,
    val spell1Id: Int,
    val spell2Id: Int,
    val stats: Stats,
    val teamId: Int,
    val timeline: TimelineX
)