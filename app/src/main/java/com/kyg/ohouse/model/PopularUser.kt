package com.kyg.ohouse.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularUser(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("introduction") val introduction: String,
    @SerializedName("id") val id: Long,
) : Parcelable