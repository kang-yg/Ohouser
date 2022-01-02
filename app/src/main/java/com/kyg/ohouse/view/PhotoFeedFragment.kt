package com.kyg.ohouse.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.kyg.ohouse.R
import com.kyg.ohouse.adapter.PhotoFeedAdapter
import com.kyg.ohouse.adapter.RecyclerViewItemDecoration
import com.kyg.ohouse.databinding.FragPhotoFeedBinding
import com.kyg.ohouse.viewmodel.PhotoFeedFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PhotoFeedFragment : Fragment() {
    private val photoFeedFragmentViewModel: PhotoFeedFragmentViewModel by viewModels()
    lateinit var fragPhotoFeedBinding: FragPhotoFeedBinding
    private val photoFeedRecyclerView: RecyclerView by lazy {
        fragPhotoFeedBinding.photoFeedRecyclerView
    }

    private lateinit var photoFeedAdapter: PhotoFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragPhotoFeedBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.frag_photo_feed, container, false)

        return fragPhotoFeedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPhotoFeedAdapter()
        observePopularCardDetailData()
        setPagingData()
    }

    private fun setPhotoFeedAdapter() {
        photoFeedAdapter = PhotoFeedAdapter(photoFeedFragmentViewModel)
        photoFeedRecyclerView.adapter = photoFeedAdapter
        photoFeedRecyclerView.addItemDecoration(RecyclerViewItemDecoration(10, RecyclerViewItemDecoration.Direction.BOTTOM))
    }

    private fun setPagingData() {
        lifecycleScope.launch {
            photoFeedFragmentViewModel.setPaging().collectLatest { pagingData ->
                photoFeedAdapter.submitData(pagingData)
            }
        }
    }

    private fun observePopularCardDetailData() {
        photoFeedFragmentViewModel.popularCardDetailData.observe(viewLifecycleOwner, {
            (activity as MainActivity).changeFragment(
                fragmentName = MainActivity.FragmentName.CARD,
                cardDetail = it
            )
        })
    }
}