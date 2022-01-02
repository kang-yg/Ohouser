package com.kyg.ohouse.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kyg.ohouse.MyApplication.Companion.logInInfo
import com.kyg.ohouse.model.SignInResponse

class MainActivityViewModel : BaseViewModel() {
    val signInBtFlag: MutableLiveData<Boolean> = MutableLiveData()

    fun signInBtClicked() {
        if (signInBtFlag.value == null) {
            signInBtFlag.value = false
        } else {
            signInBtFlag.value?.let {
                signInBtFlag.value = !it
            }
        }
    }

    fun logoutBtClicked() {
        logInInfo.value = SignInResponse()
    }
}