package com.example.lostpet.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    val petAvatar: String,
    @SerializedName("typePet")
    val petType: String,
    @SerializedName("color")
    val petColor: String,
    @SerializedName("description")
    val petDescription: String,
    @SerializedName("latitude")
    val petLatitude: String,
    @SerializedName("longitude")
    val petLongitude: String,
    @SerializedName("userId")
    val petUserId: Int
): Parcelable
