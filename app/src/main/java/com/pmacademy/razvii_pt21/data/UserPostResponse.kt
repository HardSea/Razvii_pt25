package com.pmacademy.razvii_pt21.data

import com.google.gson.annotations.SerializedName

data class UserPostResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)

