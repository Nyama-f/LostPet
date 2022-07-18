package com.example.lostpet.data.model

import com.google.gson.annotations.SerializedName

data class EditData(
    @SerializedName("name")
    val name: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("phone")
    val mobilePhone: String
){

}