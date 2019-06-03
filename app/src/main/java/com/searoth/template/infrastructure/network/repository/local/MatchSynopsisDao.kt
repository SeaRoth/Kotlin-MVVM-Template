package com.searoth.template.infrastructure.network.repository.local

import androidx.room.*
import com.searoth.template.domain.models.league.MatchSynopsis
import io.reactivex.Single

@Dao
interface MatchSynopsisDao {
    @Query("SELECT * FROM match_synopsis") fun getMatches() : Single<List<MatchSynopsis>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertMatches(list: List<MatchSynopsis>)
    @Update fun updateMatches(list: List<MatchSynopsis>): Int
}