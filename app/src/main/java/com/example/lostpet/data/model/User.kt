package com.example.lostpet.data.model

data class User(
    val userName: String,
    val userAvatar: String,
    val userLogin: String,
    val userPassword: String,
    val listOfMarks: List<Pet>
)

