package com.brudenell.harupiece.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brudenell.harupiece.MainActivity
import com.brudenell.harupiece.R
import com.brudenell.harupiece.adapter.GroupListAdapter
import com.brudenell.harupiece.databinding.FragmentMainListBinding
import com.brudenell.harupiece.model.Group

class MainListFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentMainListBinding

    val tempGroupList = listOf(
        Group("신규 챌린지", ""),
        Group("인기 챌린지", ""),
        Group("최근 본 챌린지", ""),
        Group("참여 중인 챌린지", ""),
        Group("운동 챌린지", ""),
        Group("게임 챌린지", ""),
        Group("공부 챌린지", ""),
        Group("일상 챌린지", ""),
        Group("효도 챌린지", ""),
        Group("취미 챌린지", ""),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        binding = FragmentMainListBinding.inflate(layoutInflater)

        binding.run {

            recyclerViewMainList.run {
                adapter = GroupListAdapter(tempGroupList)
                layoutManager = LinearLayoutManager(context)
                setItemViewCacheSize(10)
            }

            buttonMainListCreateChallenge.run {
                setOnClickListener{
                    findNavController().navigate(R.id.action_mainListFragment_to_createChallengeFragment)
                }
            }
        }

        return binding.root
    }
}