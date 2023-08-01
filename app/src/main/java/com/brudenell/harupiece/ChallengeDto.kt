package com.brudenell.harupiece

import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class User(
    val email: String,
    val username: String,
    val password: String
)

data class SignResponse(
    val token: String?,
    val email: String,
    val username: String?,
    val password: String?,
    val id: String?,
    val challengeEntityTitle: String?,
    val recentViewChallengeId: String?
)

// 나중에 수정
data class ChallengeRequest(
    val title: String
)

data class Participate(
    val id: String
)

data class ChallengeListResponse(
    val error: String?,
    val data: List<ChallengeResponse>?
)

data class ChallengeResponse(
    val id: String,
    val title: String,
    val done: Boolean,
    val addedDate: String,
    val participantCount: Int?,
    val challengerIds: List<String>
)

class ChallengeDto(val context: Context) {
    fun signIn(user: User, callback: (SignResponse) -> Unit) {
        RetrofitManager.challengeService.signIn(user)
            .enqueue(object : Callback<SignResponse> {
                override fun onResponse(
                    call: Call<SignResponse>,
                    response: Response<SignResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignResponse>, t: Throwable) {
                    Toast.makeText(context, "signIn 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun signUp(user: User, callback: (SignResponse) -> Unit) {
        RetrofitManager.challengeService.signUp(user)
            .enqueue(object : Callback<SignResponse> {
                override fun onResponse(
                    call: Call<SignResponse>,
                    response: Response<SignResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        Toast.makeText(context, "회원 가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignResponse>, t: Throwable) {
                    Toast.makeText(context, "signUp 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun createChallenge(challengeRequest: ChallengeRequest, callback: (ChallengeListResponse) -> Unit) {
        RetrofitManager.challengeService.createChallenge((context as MainActivity).token, challengeRequest)
            .enqueue(object : Callback<ChallengeListResponse> {
                override fun onResponse(
                    call: Call<ChallengeListResponse>,
                    response: Response<ChallengeListResponse>
                ) {
                    Log.d("azaaza", context.token)
                    Log.d("azaaza", response.toString())
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        Toast.makeText(context, "챌린지 생성 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ChallengeListResponse>, t: Throwable) {
                    Toast.makeText(context, "createChallenge 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun participateChallenge(participate: Participate, callback: (ChallengeListResponse) -> Unit) {
        RetrofitManager.challengeService.participateChallenge((context as MainActivity).token, participate)
            .enqueue(object : Callback<ChallengeListResponse> {
                override fun onResponse(
                    call: Call<ChallengeListResponse>,
                    response: Response<ChallengeListResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        Toast.makeText(context, "챌린지 참가 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ChallengeListResponse>, t: Throwable) {
                    Toast.makeText(context, "participateChallenge 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }
}