package com.kyg.ohouse.model

import com.google.gson.annotations.SerializedName

data class Popular(
    @SerializedName("popular_cards") val popularCards: ArrayList<PopularCard>,
    @SerializedName("popular_users") val popularUsers: ArrayList<PopularUser>,
)
