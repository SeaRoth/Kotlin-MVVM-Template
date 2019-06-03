package com.searoth.template.domain.models.league

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summoner")
data class Summoner @JvmOverloads constructor(

    @NonNull @PrimaryKey val accountId: String,

    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "profileIconId")
    val profileIconId: Int,

    @ColumnInfo(name = "puuid")
    val puuid: String,

    @ColumnInfo(name = "revisionDate")
    val revisionDate: Long,

    @ColumnInfo(name = "summonerLevel")
    val summonerLevel: Int
)