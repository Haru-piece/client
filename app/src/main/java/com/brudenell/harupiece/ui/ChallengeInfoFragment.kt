package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brudenell.harupiece.R
import com.brudenell.harupiece.databinding.FragmentChallengeInfoBinding

class ChallengeInfoFragment : Fragment() {

    lateinit var binding: FragmentChallengeInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeInfoBinding.inflate(layoutInflater)

        val title = arguments?.getString("title")
        val members = arguments?.getInt("members")

        binding.run {
            textViewChallengeInfoTitle.text = title
            textViewChallengeInfoMembers.text = "${members}명"
        }

        return binding.root
    }
}