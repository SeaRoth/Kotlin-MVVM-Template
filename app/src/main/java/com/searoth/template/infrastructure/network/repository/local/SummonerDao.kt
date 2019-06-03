package com.searoth.template.infrastructure.network.repository.local

import androidx.room.*
import com.searoth.template.domain.models.league.Summoner
import io.reactivex.Single

@Dao
interface SummonerDao {
    @Query("SELECT * FROM SUMMONER where name = :n LIMIT 1") fun getSummoner(n: String) : Single<Summoner>
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertSummoner(summoner: Summoner)
    @Update fun updateSummoner(summoner: Summoner): Int
}