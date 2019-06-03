package com.searoth.template.infrastructure.network.repository.local

import androidx.room.*
import com.searoth.template.domain.models.league.Match
import io.reactivex.Single

@Dao
interface MatchDao {
    @Query("SELECT * FROM single_match") fun getMatches() : Single<List<Match>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertMatches(list: List<Match>)
    @Update fun updateMatches(list: List<Match>): Int
}