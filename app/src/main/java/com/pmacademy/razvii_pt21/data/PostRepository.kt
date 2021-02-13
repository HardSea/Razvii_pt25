package com.pmacademy.razvii_pt21.data

import com.pmacademy.razvii_pt21.domain.PostMapper
import com.pmacademy.razvii_pt21.domain.UserPostModel
import com.pmacademy.razvii_pt21.tools.PostService


class PostRepository(
    private val postService: PostService,
    private val postMapper: PostMapper
) {
    fun getPostsAndUserInfo(): List<UserPostModel>? {
        return try {
            val listOfPosts = postService.getPostsList().execute().body()
            listOfPosts?.let(
                postMapper::map
            )
        } catch (e: Exception) {
            null
        }
    }
}