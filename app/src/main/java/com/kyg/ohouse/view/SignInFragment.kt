package com.kyg.ohouse.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kyg.ohouse.MyApplication.Companion.logInInfo
import com.kyg.ohouse.R
import com.kyg.ohouse.databinding.FragSigninBinding
import com.kyg.ohouse.viewmodel.SignInFragmentViewModel

class SignInFragment : Fragment() {
    private val signInFragmentViewModel: SignInFragmentViewModel by viewModels()
    private lateinit var fragSignInBinding: FragSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragSignInBinding =
            DataBindingUtil.inflate(inflater, R.layout.frag_signin, container, false)
        return fragSignInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragSignInBinding.signInFragmentViewModel = signInFragmentViewModel

        observeLogInInfo()
    }

    private fun observeLogInInfo() {
        logInInfo.observe(viewLifecycleOwner, {
            if (!it.result) {
                if (it.errorMsg != "") {
                    Toast.makeText(activity, it.errorMsg, Toast.LENGTH_SHORT).show()
                }
            } else {
                activity?.supportFragmentManager?.popBackStack()
            }
        })
    }
}