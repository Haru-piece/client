package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.brudenell.harupiece.MainActivity
import com.brudenell.harupiece.Participate
import com.brudenell.harupiece.R
import com.brudenell.harupiece.databinding.FragmentChallengeInfoBinding

class ChallengeInfoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentChallengeInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        binding = FragmentChallengeInfoBinding.inflate(layoutInflater)

        val title = arguments?.getString("title")
        val members = arguments?.getInt("members")
        val owner = arguments?.getBoolean("owner")
        val id = arguments?.getString("id") ?: ""
        val participantIds = arguments?.getStringArray("participantIds")

        binding.run {
            toolbarChallengeInfo.run {
                setNavigationIcon(R.drawable.navigate_before_24px)

                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            textViewChallengeInfoTitle.text = title
            textViewChallengeInfoMembers.text = "${members}명"

            if (owner == true)
                buttonChallengeInfoParticipate.isEnabled = false

            buttonChallengeInfoParticipate.run {
                setOnClickListener {
                    val participate = Participate(
                        id
                    )

                    mainActivity.challengeDto.participateChallenge(participate) {
                        if (it.error != null) {
                            Toast.makeText(mainActivity, "챌린지 참가 실패", Toast.LENGTH_SHORT).show()
                        } else {
                            for (challenge in it.data ?: emptyList()) {
                                if (challenge.id == participate.id) {
                                    textViewChallengeInfoTitle.text = challenge.title
                                    textViewChallengeInfoMembers.text = "${challenge.participantCount}"
                                }
                            }
                            isEnabled = false
                        }
                    }
                }
            }

            mainActivity.challengeDto.getUserInfo {
                if (participantIds?.contains(it.data?.get(0)?.id) == true) {
                    buttonChallengeInfoParticipate.text = "챌린지 탈퇴하기"
                }
            }
        }

        return binding.root
    }
}