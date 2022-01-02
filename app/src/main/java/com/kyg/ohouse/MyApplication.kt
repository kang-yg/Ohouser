package com.kyg.ohouse

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kyg.ohouse.model.SignInResponse

class MyApplication : Application() {
    companion object {
        val logInInfo: MutableLiveData<SignInResponse> = MutableLiveData()
    }
}