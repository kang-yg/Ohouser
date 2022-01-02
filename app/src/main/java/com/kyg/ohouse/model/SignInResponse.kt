package com.kyg.ohouse.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("ok") val result: Boolean = false,
    @SerializedName("user_id") val userId: Long = 0,
    @SerializedName("error_msg") val errorMsg: String = "",
)