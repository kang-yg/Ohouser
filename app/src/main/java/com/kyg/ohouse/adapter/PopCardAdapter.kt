package com.kyg.ohouse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyg.ohouse.databinding.ItemPopCardImageBinding
import com.kyg.ohouse.model.PopularCard
import com.kyg.ohouse.viewmodel.BaseViewModel

class PopCardAdapter(val baseViewModel: BaseViewModel) :
    ListAdapter<PopularCard, PopCardAdapter.PopularCardViewHolder>(diffUtil) {

    inner class PopularCardViewHolder(val view: ItemPopCardImageBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(popularCard: PopularCard) {
            view.baseViewModel = baseViewModel
            view.popularCard = popularCard
            val popCardImageView: ImageView = view.popCardImage
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCardViewHolder {
        return PopularCardViewHolder(
            ItemPopCardImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularCardViewHolder, position: Int) {
        holder.bind(currentList[position])
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