package com.searoth.template.domain.models.league

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_single")
data class Match @JvmOverloads constructor(

    @NonNull @PrimaryKey val gameId: Long,

    @ColumnInfo(name = "gameCreation")
    val gameCreation: Long,

    @ColumnInfo(name = "gameDuration")
    val gameDuration: Int,

    @ColumnInfo(name = "gameMode")
    val gameMode: String,

    @ColumnInfo(name = "gameType")
    val gameType: String,

    @ColumnInfo(name = "gameVersion")
    val gameVersion: String,

    @ColumnInfo(name = "mapId")
    val mapId: Int,

    @ColumnInfo(name = "participantIdentities")
    val participantIdentities: List<ParticipantIdentity>,

    @ColumnInfo(name = "participants")
    val participants: List<Participant>,

    @ColumnInfo(name = "platformId")
    val platformId: String,

    @ColumnInfo(name = "queueId")
    val queueId: Int,

    @ColumnInfo(name = "seasonId")
    val seasonId: Int,

    @ColumnInfo(name = "teams")
    val teams: List<Team>
)