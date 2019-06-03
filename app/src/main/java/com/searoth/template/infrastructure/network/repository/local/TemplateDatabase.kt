package com.searoth.template.infrastructure.network.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.searoth.template.domain.models.league.Match
import com.searoth.template.domain.models.league.MatchSynopsis
import com.searoth.template.domain.models.league.Summoner

@Database(entities = [Summoner::class, MatchSynopsis::class, Match::class], version = 2)
@TypeConverters(com.searoth.template.infrastructure.network.repository.TypeConverters::class)
abstract class TemplateDatabase : RoomDatabase() {
    abstract fun summonerDao(): SummonerDao
    abstract fun matchSynopsisDao(): MatchSynopsisDao
    abstract fun matchDao(): MatchDao

    companion object {
        private var INSTANCE: TemplateDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): TemplateDatabase {
            synchronized(lock){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TemplateDatabase::class.java, "Template.db").build()
                }
                return INSTANCE!!
            }
        }
    }
}