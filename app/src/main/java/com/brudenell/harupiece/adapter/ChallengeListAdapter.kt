package com.brudenell.harupiece.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brudenell.harupiece.R
import com.brudenell.harupiece.databinding.ListItemChallengeBinding
import com.brudenell.harupiece.model.Challenge
import com.bumptech.glide.Glide

class ChallengeListAdapter(): ListAdapter<Challenge, ChallengeListAdapter.ChallengeViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Challenge>() {
            override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        return ChallengeViewHolder(
            ListItemChallengeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ChallengeViewHolder(val binding: ListItemChallengeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenge) {
            binding.run {
                root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("title", challenge.title)
                    bundle.putInt("members", challenge.members)
                    it.findNavController().navigate(R.id.action_mainListFragment_to_challengeInfoFragment, bundle)
                }
                textViewListItemChallengeTitle.text = challenge.title
                textViewListItemChallengeCategory.text = challenge.category
                textViewListItemChallengeMembers.text = "${challenge.members}"

                Glide.with(imageViewListItemChallengeThumb)
                    .load(challenge.imageUrl)
                    .into(imageViewListItemChallengeThumb)
            }
        }
    }
}