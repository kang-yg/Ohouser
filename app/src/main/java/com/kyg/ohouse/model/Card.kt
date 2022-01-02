package com.kyg.ohouse.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("cards") val cards: ArrayList<PopularCard>
)