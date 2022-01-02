package com.kyg.ohouse.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.kyg.ohouse.R
import com.kyg.ohouse.adapter.PopCardAdapter
import com.kyg.ohouse.databinding.FragCardDetailBinding
import com.kyg.ohouse.model.CardDetail
import com.kyg.ohouse.viewmodel.CardDetailFragmentViewModel

class CardDetailFragment : Fragment() {
    private var cardDetail: CardDetail? = null
    private val cardDetailFragmentViewModel: CardDetailFragmentViewModel by viewModels()
    lateinit var fragCardDetailBinding: FragCardDetailBinding
    private val cardDetailItemPhotoFeed by lazy {
        fragCardDetailBinding.cardDetailItemPhotoFeed
    }
    private val cardDetailItemUserInfo by lazy {
        fragCardDetailBinding.cardDetailItemUserInfo
    }
    private val cardDetailItemPopCard by lazy {
        fragCardDetailBinding.cardDetailItemPopCard
    }
    private val itemPhotoFeedImage by lazy {
        cardDetailItemPhotoFeed.itemPhotoFeedImage
    }
    private val itemPhotoFeedDescription by lazy {
        cardDetailItemPhotoFeed.itemPhotoFeedDescription
    }
    private val popUserName by lazy {
        cardDetailItemUserInfo.popUserName
    }
    private val popUserContent by lazy {
        cardDetailItemUserInfo.popUserContent
    }
    private val popCardTitle by lazy {
        cardDetailItemPopCard.popCardTitle
    }
    private val popCardRecyclerView by lazy {
        cardDetailItemPopCard.popCardRecyclerView
    }

    private lateinit var popCardAdapter: PopCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragCardDetailBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.frag_card_detail, container, false)
        return fragCardDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        fragCardDetailBinding.baseViewModel = cardDetailFragmentViewModel

        setCardDetailItemPhotoFeed()
        setCardDetailItemUserInfo()
        setCardDetailItemPopCard()
        observePopularCardDetailData()
        observePopularUserDetailData()
    }

    private fun initData() {
        arguments.let {
            cardDetail = it?.getParcelable("CardDetail")
        }
    }

    private fun setCardDetailItemPhotoFeed() {
        val cardInfo = cardDetail?.card
        fragCardDetailBinding.popularCard = cardInfo
        if (cardInfo?.imageURL?.isNotEmpty() == true) {
            Glide.with(itemPhotoFeedImage.context).load(cardInfo.imageURL).centerCrop()
                .into(itemPhotoFeedImage)
        }
    }

    private fun setCardDetailItemUserInfo() {
        val userInfo = cardDetail?.user
        fragCardDetailBinding.popularUser = userInfo
    }

    private fun setCardDetailItemPopCard() {
        fragCardDetailBinding.popCardTitleText = getString(R.string.recommend_card_title)
        popCardAdapter = PopCardAdapter(cardDetailFragmentViewModel)
        popCardRecyclerView.adapter = popCardAdapter
        popCardAdapter.submitList(cardDetail?.recommendCards)
    }

    private fun observePopularCardDetailData() {
        cardDetailFragmentViewModel.popularCardDetailData.observe(
            viewLifecycleOwner, {
                (activity as MainActivity).changeFragment(
                    fragmentName = MainActivity.FragmentName.CARD,
                    cardDetail = it
                )
            }
        )
    }

    private fun observePopularUserDetailData() {
        cardDetailFragmentViewModel.popularUserDetailData.observe(
            viewLifecycleOwner, {
                (activity as MainActivity).changeFragment(
                    fragmentName = MainActivity.FragmentName.USER,
                    popularUser = it
                )
            }
        )
    }

    companion object {
        fun instance(cardDetail: CardDetail): CardDetailFragment {
            return CardDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("CardDetail", cardDetail)
                }
            }
        }
    }
}