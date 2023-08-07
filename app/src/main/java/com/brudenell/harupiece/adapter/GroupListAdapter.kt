package com.brudenell.harupiece.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brudenell.harupiece.MainActivity
import com.brudenell.harupiece.databinding.ListItemGroupBinding
import com.brudenell.harupiece.model.Challenge
import com.brudenell.harupiece.model.Group

class GroupListAdapter(val mainActivity: MainActivity, val groupList: List<Group>): RecyclerView.Adapter<GroupListAdapter.GroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(
            ListItemGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(groupList[position])
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    inner class GroupViewHolder(val binding: ListItemGroupBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(group: Group) {
            binding.run {
                textViewListItemGroupName.text = group.name

                val challengeListAdapter = ChallengeListAdapter()

                recyclerViewListItemGroup.run {
                    adapter = challengeListAdapter
                    layoutManager = LinearLayoutManager(recyclerViewListItemGroup.context, LinearLayoutManager.HORIZONTAL, false)
                }

                val tempList = mutableListOf<Challenge>()

                when (group.name) {
                    "참여 중인 챌린지(API)" -> {
                        mainActivity.challengeDto.getUserChallenge {
                            for (challenge in it.data ?: emptyList()) {
                                tempList.add(
                                    Challenge(
                                        challenge.id,
                                        challenge.title,
                                        "https://picsum.photos/${150+adapterPosition}/${150+adapterPosition}",
                                        "카테고리${adapterPosition}",
                                        challenge.participantCount ?: 0,
                                        challenge.participantIds
                                    )
                                )
                            }
                            challengeListAdapter.submitList(tempList)
                        }
                    }
                    "신규 챌린지(API)" -> {
                        mainActivity.challengeDto.getChallenge(group.sort) {
                            for (challenge in it.data ?: emptyList()) {
                                tempList.add(
                                    Challenge(
                                        challenge.id,
                                        challenge.title,
                                        "https://picsum.photos/${150+adapterPosition}/${150+adapterPosition}",
                                        "카테고리${adapterPosition}",
                                        challenge.participantCount ?: 0,
                                        challenge.participantIds
                                    )
                                )
                            }
                            challengeListAdapter.submitList(tempList)
                        }
                    }
                    "인기 챌린지(API)" -> {
                        mainActivity.challengeDto.getChallenge(group.sort) {
                            for (challenge in it.data ?: emptyList()) {
                                tempList.add(
                                    Challenge(
                                        challenge.id,
                                        challenge.title,
                                        "https://picsum.photos/${150+adapterPosition}/${150+adapterPosition}",
                                        "카테고리${adapterPosition}",
                                        challenge.participantCount ?: 0,
                                        challenge.participantIds
                                    )
                                )
                            }
                            challengeListAdapter.submitList(tempList)
                        }
                    }
                    else -> {
                        for (i in 1 .. 10) {
                            tempList.add(
                                Challenge(
                                    "$i",
                                    "${group.name}${i}",
                                    "https://picsum.photos/${150+i+10*adapterPosition}/${150+i+10*adapterPosition}",
                                    "카테고리${adapterPosition}",
                                    80,
                                    emptyList()
                                )
                            )
                        }
                        challengeListAdapter.submitList(tempList)
                    }
                }

//                recyclerViewListItemGroup.run {
//                    adapter = ChallengeListAdapter()
//                    (adapter as ChallengeListAdapter).submitList(tempList)
//                    layoutManager = LinearLayoutManager(recyclerViewListItemGroup.context, LinearLayoutManager.HORIZONTAL, false)
//                }
            }
        }
    }
}