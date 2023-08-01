package com.brudenell.harupiece

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChallengeService {
    @POST("auth/signup")
    fun signUp(@Body user: User): Call<SignResponse>

    @POST("auth/signin")
    fun signIn(@Body user: User): Call<SignResponse>

    @POST("challenge")
    fun createChallenge(
        @Header("Authorization") token: String,
        @Body challengeRequest: ChallengeRequest
    ): Call<ChallengeListResponse>

    @POST("challenge/participate")
    fun participateChallenge(
        @Header("Authorization") token: String,
        @Body participate: Participate
    ): Call<ChallengeListResponse>
}