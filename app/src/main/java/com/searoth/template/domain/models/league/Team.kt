package com.searoth.template.domain.models.league

data class Team(
    val bans: List<Any>,
    val baronKills: Int,
    val dominionVictoryScore: Int,
    val dragonKills: Int,
    val firstBaron: Boolean,
    val firstBlood: Boolean,
    val firstDragon: Boolean,
    val firstInhibitor: Boolean,
    val firstRiftHerald: Boolean,
    val firstTower: Boolean,
    val inhibitorKills: Int,
    val riftHeraldKills: Int,
    val teamId: Int,
    val towerKills: Int,
    val vilemawKills: Int,
    val win: String
)