package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

                    mainActivity.onBackPressedDispatcher.onBackPressed()
                }
            }
        }

        return binding.root
    }
}