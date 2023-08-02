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
import com.brudenell.harupiece.RetrofitManager
import com.brudenell.harupiece.User
import com.brudenell.harupiece.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.run {
            buttonLoginSignIn.setOnClickListener {
                val user = User(
                    textInputEditTextLoginId.text.toString(),
                    textInputEditTextLoginId.text.toString(),
                    textInputEditTextLoginPw.text.toString()
                )

                mainActivity.challengeDto.signIn(user) {
                    if (it.token != null) {
                        RetrofitManager.token = "Bearer ${it.token}"
                    }

                    Toast.makeText(mainActivity, "로그인 되었습니다", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_mainListFragment)
                }
            }
            buttonLoginSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
        }

        return binding.root
    }
}