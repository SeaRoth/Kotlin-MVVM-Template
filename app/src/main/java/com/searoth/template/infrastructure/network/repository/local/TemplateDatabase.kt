package com.searoth.template.infrastructure.network.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.searoth.template.domain.models.league.Summoner

@Database(entities = [Summoner::class], version = 1)
abstract class TemplateDatabase : RoomDatabase() {
    abstract fun summonerDao(): SummonerDao

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