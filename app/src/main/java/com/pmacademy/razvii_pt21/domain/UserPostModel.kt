package com.pmacademy.razvii_pt21.domain

data class UserPostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val userStatus: UserStatusType
)