package com.kyg.ohouse.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularCard(
    @SerializedName("user_id") val userId: Long,
    @SerializedName("img_url") val imageURL: String,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Long,
) : Parcelable