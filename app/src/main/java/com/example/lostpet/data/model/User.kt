package com.example.lostpet.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val userId: String?,
    @SerializedName("name")
    val userName: String,
    @SerializedName("avatar")
    val userAvatar: String,
    @SerializedName("login")
    val userLogin: String,
    @SerializedName("password")
    val userPassword: String,
//    @SerializedName("listOfMarks")
//    val listOfMarks: MutableList<Pet>
)

