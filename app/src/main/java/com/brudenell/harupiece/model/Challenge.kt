package com.brudenell.harupiece.model

data class Challenge(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: String,
    val members: Int,
)
