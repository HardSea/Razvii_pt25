package com.pmacademy.razvii_pt21.data.repository

import com.pmacademy.razvii_pt21.data.mapper.PostMapper
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.datasource.api.PostsReposApi

class PostRepository(
    private val postsReposApi: PostsReposApi,
    private val postMapper: PostMapper
) {
    fun getPostsAndUserInfo(): List<UserPostModel>? {
        return try {
            val listOfPosts = postsReposApi.getPostsList().execute().body()
            listOfPosts?.let(
                postMapper::map
            )
        } catch (e: Exception) {
            null
        }
    }
}