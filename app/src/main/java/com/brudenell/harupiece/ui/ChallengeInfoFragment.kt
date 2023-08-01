package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.run {
            textViewChallengeInfoTitle.text = title
            textViewChallengeInfoMembers.text = "${members}명"

            if (owner == true)
                buttonChallengeInfoParticipate.isEnabled = false

            buttonChallengeInfoParticipate.run {
                setOnClickListener {
                    val participate = Participate(
                        "2c9f8c6f89b00f600189b216a8160019"
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
        }

        return binding.root
    }
}