package com.kyg.ohouse.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.kyg.ohouse.R
import com.kyg.ohouse.adapter.PopCardAdapter
import com.kyg.ohouse.adapter.PopUserAdapter
import com.kyg.ohouse.databinding.FragHomeBinding
import com.kyg.ohouse.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private lateinit var fragHomeBinding: FragHomeBinding
    private val popCardRecyclerView: RecyclerView by lazy {
        fragHomeBinding.itemPopCard.popCardRecyclerView
    }
    private val popUserRecyclerView: RecyclerView by lazy {
        fragHomeBinding.itemPopUser.popUserRecyclerView
    }

    private lateinit var popCardAdapter: PopCardAdapter
    private lateinit var popUserAdapter: PopUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragHomeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.frag_home, container, false)
        return fragHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPopularTitle()
        setPopularAdapter()
        observeHomeData()
        observePopularCardDetailData()
        observePopularUserDetailData()
    }

    private fun setPopularTitle() {
        fragHomeBinding.popCardTitleText = getString(R.string.pop_card_title)
        fragHomeBinding.popUserTitleText = getString(R.string.pop_user_title)

    }

    private fun setPopularAdapter() {
        popCardAdapter = PopCardAdapter(homeFragmentViewModel)
        popUserAdapter = PopUserAdapter(homeFragmentViewModel)
        popCardRecyclerView.adapter = popCardAdapter
        popUserRecyclerView.adapter = popUserAdapter
    }

    private fun observeHomeData() {
        homeFragmentViewModel.homeData.observe(viewLifecycleOwner, {
            popCardAdapter.submitList(it.popularCards)
            popUserAdapter.submitList(it.popularUsers)
        })
    }

    private fun observePopularCardDetailData() {
        homeFragmentViewModel.popularCardDetailData.observe(
            viewLifecycleOwner, {
                (activity as MainActivity).changeFragment(
                    fragmentName = MainActivity.FragmentName.CARD,
                    cardDetail = it
                )
            }
        )
    }

    private fun observePopularUserDetailData() {
        homeFragmentViewModel.popularUserDetailData.observe(
            viewLifecycleOwner, {
                (activity as MainActivity).changeFragment(
                    fragmentName = MainActivity.FragmentName.USER,
                    popularUser = it
                )
            }
        )
    }
}