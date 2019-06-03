package com.searoth.template.domain.models.league

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_synopsis")
data class MatchSynopsis @JvmOverloads constructor(

    @NonNull @PrimaryKey val gameId: Long,

    @ColumnInfo(name = "champion")
    val champion: Int,

    @ColumnInfo(name = "lane")
    val lane: String,

    @ColumnInfo(name = "platformId")
    val platformId: String,

    @ColumnInfo(name = "queue")
    val queue: Int,

    @ColumnInfo(name = "role")
    val role: String,

    @ColumnInfo(name = "season")
    val season: Int,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)