package com.pmacademy.razvii_pt21.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class UserPostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val userStatus: UserStatusType
)