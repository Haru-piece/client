package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.brudenell.harupiece.MainActivity
import com.brudenell.harupiece.R
import com.brudenell.harupiece.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentUserInfoBinding

    lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        binding = FragmentUserInfoBinding.inflate(layoutInflater)

        binding.run {
            toolbarUserInfo.run {
                setNavigationIcon(R.drawable.navigate_before_24px)

                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            mainActivity.challengeDto.getUserInfo {
                textViewUserInfoUserName.text = it.data?.get(0)?.id
                username = it.data?.get(0)?.username ?: ""

                mainActivity.challengeDto.getRelationBadge {
                    if (it.error == null) {
                        for (relationBadge in it.data ?: emptyList()) {
                            if (relationBadge.userName == username) {
                                textViewUserInfoBadge.text = relationBadge.badgeName
                                break
                            }
                        }
                    }
                }
            }
        }

        return binding.root
    }
}