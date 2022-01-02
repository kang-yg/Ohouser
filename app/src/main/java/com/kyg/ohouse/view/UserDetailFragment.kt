package com.kyg.ohouse.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kyg.ohouse.R
import com.kyg.ohouse.databinding.FragUserDetailBinding
import com.kyg.ohouse.model.PopularUser

class UserDetailFragment : Fragment() {
    private var popularUser: PopularUser? = null
    lateinit var fragUserDetailBinding: FragUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragUserDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.frag_user_detail, container, false)
        return fragUserDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setItemUserDetail()
    }

    private fun initData() {
        arguments.let {
            popularUser = it?.getParcelable("PopularUser")
        }
    }

    private fun setItemUserDetail() {
        fragUserDetailBinding.popularUser = popularUser
    }

    companion object {
        fun instance(popularUser: PopularUser): UserDetailFragment {
            return UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("PopularUser", popularUser)
                }
            }
        }
    }
}