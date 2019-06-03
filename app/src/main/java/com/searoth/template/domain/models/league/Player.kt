package com.searoth.template.domain.models.league

data class Player(
    val accountId: String,
    val currentAccountId: String,
    val currentPlatformId: String,
    val matchHistoryUri: String,
    val platformId: String,
    val profileIcon: Int,
    val summonerId: String,
    val summonerName: String
)