package com.kyg.ohouse.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kyg.ohouse.MyApplication.Companion.logInInfo
import com.kyg.ohouse.R
import com.kyg.ohouse.adapter.ViewPagerAdapter
import com.kyg.ohouse.databinding.ActivityMainBinding
import com.kyg.ohouse.model.CardDetail
import com.kyg.ohouse.model.PopularUser
import com.kyg.ohouse.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var mainActivityMainBinding: ActivityMainBinding
    private val mainTapLayout: TabLayout by lazy {
        mainActivityMainBinding.mainTapLayout
    }
    private val mainViewPager: ViewPager2 by lazy {
        mainActivityMainBinding.mainViewPager
    }
    private val mainFragmentContainerView: FragmentContainerView by lazy {
        mainActivityMainBinding.mainFragmentContainerView
    }
    private val signInConstraintGroup: Group by lazy {
        mainActivityMainBinding.signInConstraintGroup
    }
    private val logoutBt: Button by lazy {
        mainActivityMainBinding.logoutBt
    }

    private val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityMainBinding.mainActivityViewModel = mainActivityViewModel

        setViewPager()
        setTapLayout()
        observeSignInBt()
        observeLogInInfo()
    }

    private fun setTapLayout() {
        val tapText: ArrayList<String> =
            arrayListOf(getString(R.string.home), getString(R.string.photoFeed))

        TabLayoutMediator(mainTapLayout, mainViewPager) { tabLayout, position ->
            tabLayout.text = tapText[position]
        }.attach()
    }

    private fun setViewPager() {
        mainViewPager.adapter = ViewPagerAdapter(this)
    }

    private fun observeSignInBt() {
        val signInBtFlag = mainActivityViewModel.signInBtFlag
        signInBtFlag.observe(this, {
            if (signInBtFlag.value != null) {
                changeFragment(fragmentName = FragmentName.SIGN_IN)
            }
        })
    }

    private fun observeLogInInfo() {
        logInInfo.observe(this, {
            if (it.result) {
                signInConstraintGroup.visibility = View.GONE
                logoutBt.visibility = View.VISIBLE
            } else {
                signInConstraintGroup.visibility = View.VISIBLE
                logoutBt.visibility = View.GONE
            }
        })
    }

    fun changeFragment(
        fragmentName: FragmentName,
        cardDetail: CardDetail? = null,
        popularUser: PopularUser? = null
    ) {
        val fragmentTransaction: FragmentTransaction

        when (fragmentName) {
            FragmentName.SIGN_IN -> {
                mainFragmentContainerView.visibility = View.VISIBLE
                if (fragmentManager.findFragmentByTag("SignInFragment") == null) {
                    val signInFragment = SignInFragment()
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(
                        mainFragmentContainerView.id,
                        signInFragment,
                        "SignInFragment"
                    )
                    if (!signInFragment.isAdded) {
                        fragmentTransaction.addToBackStack(null)
                    }
                    fragmentTransaction.commit()
                }
            }

            FragmentName.CARD -> {
                cardDetail?.let {
                    mainFragmentContainerView.visibility = View.VISIBLE
                    val cardDetailFragment = CardDetailFragment.instance(it)
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(mainFragmentContainerView.id, cardDetailFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
            }

            FragmentName.USER -> {
                popularUser?.let {
                    mainFragmentContainerView.visibility = View.VISIBLE
                    val userDetailFragment = UserDetailFragment.instance(it)
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(mainFragmentContainerView.id, userDetailFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
            }
        }
    }

    enum class FragmentName {
        SIGN_IN, CARD, USER
    }
}