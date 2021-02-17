package com.pmacademy.razvii_pt21.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmacademy.razvii_pt21.data.model.UserStatusType

@Entity(tableName = "user_post_table")
data class UserPost(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)