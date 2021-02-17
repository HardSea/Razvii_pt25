package com.pmacademy.razvii_pt21.datasource.local

import androidx.room.*

@Dao
interface UserPostDao {

    @Delete
    suspend fun deleteUserPost(userPost: UserPost)

    @Update
    suspend fun updateUserPost(userPost: UserPost)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserPost(userPost: UserPost)

    @Query("SELECT * FROM user_post_table ORDER BY id ASC")
    fun getAllUserPosts(): List<UserPost>

    @Query("SELECT id FROM user_post_table WHERE id ORDER BY id ASC LIMIT 1")
    fun getMinLocalUserPostId(): Int

    @Query("DELETE FROM user_post_table")
    suspend fun clearUserPost()

    @Query("DELETE FROM user_post_table WHERE id = :id")
    suspend fun deleteUserPostById(id: Int)

}