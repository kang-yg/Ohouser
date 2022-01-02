package com.kyg.ohouse.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyg.ohouse.api.RetrofitFactory
import com.kyg.ohouse.api.RetrofitService
import com.kyg.ohouse.model.CardDetail
import com.kyg.ohouse.model.PopularCard
import com.kyg.ohouse.model.PopularUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    val popularCardDetailData: MutableLiveData<CardDetail> = MutableLiveData()
    val popularUserDetailData: MutableLiveData<PopularUser> = MutableLiveData()

    private fun requestCardDetail(popularCard: PopularCard) {
        try {
            val call = RetrofitFactory.createRetrofit()
                .create(RetrofitService::class.java)
                .getCardDetail(popularCard.id)

            call.enqueue(object : Callback<CardDetail> {
                override fun onResponse(call: Call<CardDetail>, response: Response<CardDetail>) {
                    popularCardDetailData.value = response.body()
                }

                override fun onFailure(call: Call<CardDetail>, t: Throwable) {
                    Log.e("requestCardDetail()", t.message.toString())
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun itemPopCardClicked(popularCard: PopularCard) {
        requestCardDetail(popularCard)
    }

    fun itemPopUserClicked(popularUser: PopularUser) {
        popularUserDetailData.value = popularUser
    }
}