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
    val challengeEntityTitle: List<String>?,
    val recentViewChallengeId: List<String>?
)

data class UserInfoResponse(
    val error: String?,
    val data: List<SignResponse>?
)

// 나중에 수정
data class ChallengeRequest(
    val title: String
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
    val participantIds: List<String>,
    val category: String?
)

data class RelationBadge(
    val id: String,
    val badgeName: String,
    val userName: String
)

data class RelationBadgeResponse(
    val error: String?,
    val data: List<RelationBadge>?
)

data class RelationChallenge(
    val id: String,
    val challengeName: String,
    val userName: String,
    val success: Boolean
)

data class RelationChallengeResponse(
    val error: String?,
    val data: List<RelationChallenge>?
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
        RetrofitManager.challengeService.createChallenge(RetrofitManager.token, challengeRequest)
            .enqueue(object : Callback<ChallengeListResponse> {
                override fun onResponse(
                    call: Call<ChallengeListResponse>,
                    response: Response<ChallengeListResponse>
                ) {
                    Log.d("azaaza", RetrofitManager.token)
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

    fun participateChallenge(challengeId: String, callback: (ChallengeListResponse) -> Unit) {
        RetrofitManager.challengeService.participateChallenge(RetrofitManager.token, challengeId)
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

    fun getUserChallenge(callback: (ChallengeListResponse) -> Unit) {
        RetrofitManager.challengeService.userChallenge(RetrofitManager.token)
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
                        Toast.makeText(context, "사용자가 참여 중인 챌린지 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ChallengeListResponse>, t: Throwable) {
                    Toast.makeText(context, "getUserChallenge 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getChallenge(sort: String, callback: (ChallengeListResponse) -> Unit) {
        RetrofitManager.challengeService.popularChallenge(RetrofitManager.token, sort)
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
                        Toast.makeText(context, "챌린지 조회 $sort 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ChallengeListResponse>, t: Throwable) {
                    Toast.makeText(context, "getChallenge 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getUserInfo(callback: (UserInfoResponse) -> Unit) {
        RetrofitManager.challengeService.getUserInfo(RetrofitManager.token)
            .enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        Toast.makeText(context, "유저 정보 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    Toast.makeText(context, "getUserInfo 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getRelationBadge(callback: (RelationBadgeResponse) -> Unit) {
        RetrofitManager.challengeService.getRelationBadge(RetrofitManager.token)
            .enqueue(object : Callback<RelationBadgeResponse> {
                override fun onResponse(
                    call: Call<RelationBadgeResponse>,
                    response: Response<RelationBadgeResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        Toast.makeText(context, "뱃지 관계 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RelationBadgeResponse>, t: Throwable) {
                    Toast.makeText(context, "getRelationBadge 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun withdrawChallenge(relationChallengeEntityId: String, callback: (ChallengeListResponse) -> Unit) {
        RetrofitManager.challengeService.withdrawChallenge(RetrofitManager.token, relationChallengeEntityId)
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
                        Toast.makeText(context, "챌린지 탈퇴 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ChallengeListResponse>, t: Throwable) {
                    Toast.makeText(context, "withdrawChallenge 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getRelationChallenge(callback: (RelationChallengeResponse) -> Unit) {
        RetrofitManager.challengeService.getRelationChallenge(RetrofitManager.token)
            .enqueue(object : Callback<RelationChallengeResponse> {
                override fun onResponse(
                    call: Call<RelationChallengeResponse>,
                    response: Response<RelationChallengeResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        Toast.makeText(context, "챌린지 관계 실패Dto", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RelationChallengeResponse>, t: Throwable) {
                    Toast.makeText(context, "getRelationChallenge 통신 실패", Toast.LENGTH_SHORT).show()
                }
            })
    }
}