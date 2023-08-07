package com.brudenell.harupiece

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("challenge")
    fun userChallenge(
        @Header("Authorization") token: String
    ): Call<ChallengeListResponse>

    @GET("challenge/all")
    fun popularChallenge(
        @Header("Authorization") token: String,
        @Query("sort") sort: String
    ): Call<ChallengeListResponse>

    @GET("auth/mine")
    fun getUserInfo(
        @Header("Authorization") token: String
    ): Call<UserInfoResponse>
}