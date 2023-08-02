package com.brudenell.harupiece

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.35.55.34:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    lateinit var token: String

    val challengeService: ChallengeService by lazy { retrofit.create(ChallengeService::class.java) }
}