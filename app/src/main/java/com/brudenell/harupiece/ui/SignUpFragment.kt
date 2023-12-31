package com.brudenell.harupiece.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.brudenell.harupiece.MainActivity
import com.brudenell.harupiece.R
import com.brudenell.harupiece.User
import com.brudenell.harupiece.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        binding = FragmentSignUpBinding.inflate(layoutInflater)

        binding.run {
            toolbarSignUp.run {
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

                setNavigationOnClickListener {
                    mainActivity.onBackPressedDispatcher.onBackPressed()
                }
            }

            buttonSignUpSubmit.run {
                setOnClickListener {
                    val user = User(
                        textInputEditTextSignUpId.text.toString(),
                        textInputEditTextSignUpId.text.toString(),
                        textInputEditTextSignUpPw.text.toString()
                    )

                    mainActivity.challengeDto.signUp(user) {
                        Toast.makeText(mainActivity, "회원가입 되었습니다", Toast.LENGTH_SHORT).show()
                        mainActivity.onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        }

        return binding.root
    }
}