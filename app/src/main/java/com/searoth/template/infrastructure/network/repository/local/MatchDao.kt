package com.searoth.template.infrastructure.network.repository.local

import androidx.room.*
import com.searoth.template.domain.models.league.Match
import io.reactivex.Single

@Dao
interface MatchDao {
    @Query("SELECT * FROM match_single") fun getMatches() : Single<List<Match>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertMatch(match: Match)
    @Update fun updateMatch(match: Match): Int
}