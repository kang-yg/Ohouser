package com.kyg.ohouse.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kyg.ohouse.api.RetrofitFactory
import com.kyg.ohouse.api.RetrofitService
import com.kyg.ohouse.model.Popular
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel : BaseViewModel() {
    val homeData: MutableLiveData<Popular> = MutableLiveData()

    init {
        requestHomerData()
    }

    private fun requestHomerData() {
        try {
            val call = RetrofitFactory.createRetrofit()
                .create(RetrofitService::class.java)
                .getHomeData()

            call.enqueue(object : Callback<Popular> {
                override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                    homeData.value = response.body()
                }

                override fun onFailure(call: Call<Popular>, t: Throwable) {
                    Log.e("requestHomerData()", t.message.toString())
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}