package com.kyg.ohouse.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kyg.ohouse.MyApplication.Companion.logInInfo
import com.kyg.ohouse.api.RetrofitFactory
import com.kyg.ohouse.api.RetrofitService
import com.kyg.ohouse.model.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragmentViewModel : BaseViewModel() {
    val nickname: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    init {
        nickname.value = "ohouse"
        password.value = "pass"
    }

    private fun requestSignIn() {
        try {
            val call = RetrofitFactory.createRetrofit()
                .create(RetrofitService::class.java)
                .requestSignIn(
                    nickname = nickname.value.toString().trim(),
                    password = password.value.toString().trim()
                )

            call.enqueue(object : Callback<SignInResponse> {
                override fun onResponse(
                    call: Call<SignInResponse>, response: Response<SignInResponse>
                ) {
                    logInInfo.value = response.body()
                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    Log.e("requestSignIn()", t.message.toString())
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun signInBtClicked() {
        requestSignIn()
    }
}