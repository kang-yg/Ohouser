package com.kyg.ohouse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyg.ohouse.databinding.ItemPopUserInfoBinding
import com.kyg.ohouse.model.PopularUser
import com.kyg.ohouse.viewmodel.BaseViewModel

class PopUserAdapter(val baseViewModel: BaseViewModel) :
    ListAdapter<PopularUser, PopUserAdapter.PopularUserViewHolder>(diffUtil) {
    inner class PopularUserViewHolder(val view: ItemPopUserInfoBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(popularUser: PopularUser) {
            view.baseViewModel = baseViewModel
            view.popularUser = popularUser
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularUserViewHolder {
        return PopularUserViewHolder(
            ItemPopUserInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularUserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PopularUser>() {
            override fun areItemsTheSame(oldItem: PopularUser, newItem: PopularUser): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PopularUser, newItem: PopularUser): Boolean {
                return oldItem == newItem
            }
        }
    }
}