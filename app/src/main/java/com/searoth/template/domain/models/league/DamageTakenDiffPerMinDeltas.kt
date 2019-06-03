package com.searoth.template.domain.models.league

import com.google.gson.annotations.SerializedName

data class DamageTakenDiffPerMinDeltas(
    @SerializedName("0-10")
    val zeroTen : Double
)