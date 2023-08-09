package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.brudenell.harupiece.MainActivity
import com.brudenell.harupiece.R
import com.brudenell.harupiece.databinding.FragmentChallengeInfoBinding

class ChallengeInfoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentChallengeInfoBinding
    var participateEnabled = true
    lateinit var relationChallengeId: String

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

            if (owner == true) {
                buttonChallengeInfoParticipate.text = "챌린지 탈퇴하기"
                participateEnabled = false
            }

            buttonChallengeInfoParticipate.run {
                setOnClickListener {
                    if (participateEnabled) {
                        val challengeId = id

                        mainActivity.challengeDto.participateChallenge(challengeId) {
                            if (it.error != null) {
                                Toast.makeText(mainActivity, "챌린지 참가 실패", Toast.LENGTH_SHORT).show()
                            } else {
                                for (challenge in it.data ?: emptyList()) {
                                    if (challenge.id == challengeId) {
                                        textViewChallengeInfoTitle.text = challenge.title
                                        textViewChallengeInfoMembers.text =
                                            "${challenge.participantCount}"
                                    }
                                }
                                text = "챌린지 탈퇴하기"
                                participateEnabled = false
                            }
                        }
                    } else {
                        mainActivity.challengeDto.getRelationChallenge {
                            if (it.error == null) {
                                for (relation in it.data ?: emptyList()) {
                                    if (relation.challengeName == title && relation.userName == mainActivity.username) {
                                        relationChallengeId = relation.id

                                        mainActivity.challengeDto.withdrawChallenge(relationChallengeId) {
                                            if (error != null) {
                                                Toast.makeText(mainActivity, "탈퇴 오류", Toast.LENGTH_SHORT).show()
                                            }
                                            for (challenge in it.data ?: emptyList()) {
                                                if (challenge.id == id) {
                                                    textViewChallengeInfoMembers.text = "${challenge.participantCount}"
                                                }
                                            }

                                            text = "챌린지 참가하기"
                                            participateEnabled = true
                                        }

                                        break
                                    }
                                }
                            }
                        }
                    }
                }
            }

            mainActivity.challengeDto.getUserInfo {
                if (participantIds?.contains(it.data?.get(0)?.id) == true) {
                    buttonChallengeInfoParticipate.text = "챌린지 탈퇴하기"
                    participateEnabled = false
                }
            }
        }

        return binding.root
    }
}