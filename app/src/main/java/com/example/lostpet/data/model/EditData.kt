package com.example.lostpet.data.model

import com.google.gson.annotations.SerializedName

data class EditData(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("phone")
    val mobilePhone: String? = null
)