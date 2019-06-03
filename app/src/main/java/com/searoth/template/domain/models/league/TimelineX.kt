package com.searoth.template.domain.models.league

data class TimelineX(
    val creepsPerMinDeltas: CreepsPerMinDeltas,
    val csDiffPerMinDeltas: CsDiffPerMinDeltas,
    val damageTakenDiffPerMinDeltas: DamageTakenDiffPerMinDeltas,
    val damageTakenPerMinDeltas: DamageTakenPerMinDeltas,
    val goldPerMinDeltas: GoldPerMinDeltas,
    val lane: String,
    val participantId: Int,
    val role: String,
    val xpDiffPerMinDeltas: XpDiffPerMinDeltas,
    val xpPerMinDeltas: XpPerMinDeltas
)