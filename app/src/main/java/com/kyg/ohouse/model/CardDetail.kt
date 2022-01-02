package com.kyg.ohouse.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardDetail(
    @SerializedName("card") val card: PopularCard,
    @SerializedName("user") val user: PopularUser,
    @SerializedName("recommend_cards") val recommendCards: ArrayList<PopularCard>
) : Parcelable