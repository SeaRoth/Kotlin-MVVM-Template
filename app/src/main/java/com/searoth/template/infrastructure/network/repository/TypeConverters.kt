package com.searoth.template.infrastructure.network.repository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.searoth.template.domain.models.league.Participant
import com.searoth.template.domain.models.league.ParticipantIdentity
import com.searoth.template.domain.models.league.Team

class TypeConverters {

    //participant Ids
    @TypeConverter
    fun participantIdentitiesToString(list: List<ParticipantIdentity>) : String? = Gson().toJson(list)

    @TypeConverter
    fun stringToParticipantIdentities(str: String) : List<ParticipantIdentity>? {
        val objects = Gson().fromJson(str, Array<ParticipantIdentity>::class.java) as Array<ParticipantIdentity>
        val list = objects.toList()
        return list
    }

    //part ids
    @TypeConverter
    fun identitiesToString(list: List<Participant>) : String? = Gson().toJson(list)

    @TypeConverter
    fun stringToIdentities(str: String) : List<Participant>? {
        val objects = Gson().fromJson(str, Array<Participant>::class.java) as Array<Participant>
        val list = objects.toList()
        return list
    }

    //teams
    @TypeConverter
    fun teamsToString(list: List<Team>) : String? = Gson().toJson(list)

    @TypeConverter
    fun stringToTeams(str: String) : List<Team>? {
        val objects = Gson().fromJson(str, Array<Team>::class.java) as Array<Team>
        val list = objects.toList()
        return list
    }

}