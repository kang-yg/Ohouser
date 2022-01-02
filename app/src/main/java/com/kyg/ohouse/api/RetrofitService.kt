package com.kyg.ohouse.api

import com.kyg.ohouse.model.Card
import com.kyg.ohouse.model.CardDetail
import com.kyg.ohouse.model.Popular
import com.kyg.ohouse.model.SignInResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @FormUrlEncoded
    @POST("/users/sign_in")
    fun requestSignIn(
        @Field("nickname") nickname: String,
        @Field("pwd") password: String
    ): Call<SignInResponse>

    @GET("/home")
    fun getHomeData(): Call<Popular>

    @GET("/cards")
    fun getPhotoFeedData(@Query("page") page: Int, @Query("per") per: Int): Call<Card>

    @GET("/cards/{id}")
    fun getCardDetail(@Path(value = "id") id: Long): Call<CardDetail>
}