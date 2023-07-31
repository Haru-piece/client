package com.brudenell.harupiece

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

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

interface ChallengeService {
    @POST("auth/signup")
    fun signUp(@Body user: User): Call<SignResponse>

    @POST("auth/signin")
    fun signIn(@Body user: User): Call<SignResponse>
}