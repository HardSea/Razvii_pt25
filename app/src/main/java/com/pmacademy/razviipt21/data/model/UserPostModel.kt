package com.pmacademy.razviipt21.data.model

data class UserPostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val userStatus: UserStatusType
)
