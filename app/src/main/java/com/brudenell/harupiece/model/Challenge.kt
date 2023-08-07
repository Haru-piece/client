package com.brudenell.harupiece.model

data class Challenge(
    val id: String,
    val title: String,
    val imageUrl: String,
    val category: String,
    val members: Int,
    val participantIds: List<String>
)
