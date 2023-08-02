package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.brudenell.harupiece.ChallengeRequest
import com.brudenell.harupiece.MainActivity
import com.brudenell.harupiece.R
import com.brudenell.harupiece.databinding.FragmentCreateChallengeBinding

class CreateChallengeFragment : Fragment() {

    lateinit var binding: FragmentCreateChallengeBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        binding = FragmentCreateChallengeBinding.inflate(layoutInflater)

        binding.run {
            toolbarCreateChallenge.run {
                setNavigationIcon(R.drawable.navigate_before_24px)

                setNavigationOnClickListener {
                    mainActivity.onBackPressedDispatcher.onBackPressed()
                }
            }

            buttonCreateChallengeSubmit.run {
                setOnClickListener {
                    val challengeRequest = ChallengeRequest(
                        textInputEditTextCreateChallengeTitle.text.toString()
                    )

                    mainActivity.challengeDto.createChallenge(challengeRequest) {
                        if (error != null) {
                            Toast.makeText(mainActivity, "챌린지 생성 실패", Toast.LENGTH_SHORT).show()
                        } else {
                            val bundle = Bundle()
                            bundle.putString("title", it.data?.last()?.title)
                            bundle.putInt("members", it.data?.last()?.participantCount ?: -1)
                            bundle.putBoolean("owner", true)
                            bundle.putString("id", it.data?.last()?.id)

                            findNavController().navigate(R.id.action_createChallengeFragment_to_challengeInfoFragment, bundle)
                        }
                    }
                }
            }
        }

        return binding.root
    }
}