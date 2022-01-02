package com.kyg.ohouse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyg.ohouse.databinding.ItemPhotoFeedBinding
import com.kyg.ohouse.model.PopularCard
import com.kyg.ohouse.viewmodel.PhotoFeedFragmentViewModel

class PhotoFeedAdapter(private val photoFeedFragmentViewModel: PhotoFeedFragmentViewModel) :
    PagingDataAdapter<PopularCard, PhotoFeedAdapter.PhotoFeedViewHolder>(diffUtil) {
    inner class PhotoFeedViewHolder(val view: ItemPhotoFeedBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(popularCard: PopularCard) {
            view.photoFeedFragmentViewModel = photoFeedFragmentViewModel
            view.popularCard = popularCard
            setPhotoFeedImage(popularCard)
            setPhotoFeedDescription(popularCard)
        }

        private fun setPhotoFeedImage(popularCard: PopularCard) {
            val popCardImageView: ImageView = view.itemPhotoFeedImage
            if (popularCard.imageURL.isNotEmpty()) {
                val imageURL: String = popularCard.imageURL
                Glide.with(popCardImageView.context)
                    .load(imageURL)
                    .centerCrop()
                    .into(popCardImageView)
            } else {
                popCardImageView.setImageResource(android.R.color.transparent)
            }
        }

        private fun setPhotoFeedDescription(popularCard: PopularCard) {
            val itemPhotoFeedDescription: TextView = view.itemPhotoFeedDescription
            if (popularCard.description.isNotEmpty()) {
                itemPhotoFeedDescription.text = popularCard.description
            } else {
                itemPhotoFeedDescription.text = ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoFeedViewHolder {
        return PhotoFeedViewHolder(
            ItemPhotoFeedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoFeedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PopularCard>() {
            override fun areItemsTheSame(oldItem: PopularCard, newItem: PopularCard): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PopularCard, newItem: PopularCard): Boolean {
                return oldItem == newItem
            }
        }
    }
}