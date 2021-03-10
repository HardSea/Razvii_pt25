package com.pmacademy.razviipt21.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserPost(userPost: UserPost)

    @Query("SELECT * FROM user_post_table ORDER BY id ASC")
    fun getAllUserPosts(): Flow<List<UserPost>>

    @Query("SELECT id FROM user_post_table WHERE id ORDER BY id ASC LIMIT 1")
    fun getMinLocalUserPostId(): Int

}
