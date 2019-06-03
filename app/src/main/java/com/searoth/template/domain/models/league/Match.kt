package com.searoth.template.domain.models.league

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "single_match")
data class Match @JvmOverloads constructor(

    @NonNull @PrimaryKey val gameId: Long,

    @ColumnInfo(name = "id")
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