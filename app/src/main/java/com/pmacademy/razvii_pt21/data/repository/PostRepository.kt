package com.pmacademy.razvii_pt21.data.repository

import com.pmacademy.razvii_pt21.data.mapper.PostMapper
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.datasource.local.UserPost
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.datasource.remote.api.PostsReposApi
import com.pmacademy.razvii_pt21.datasource.remote.model.UserPostResponse
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val userPostsDatabase: UserPostsDatabase,
    private val postsReposApi: PostsReposApi,
    private val postMapper: PostMapper
) {

    private fun insertUserPostFromApi(userPost: UserPost) =
        userPostsDatabase.getUserPostDao().insertUserPost(userPost)

    fun insertUserPostLocal(userId: Int, title: String, body: String) {
        userPostsDatabase.getUserPostDao().insertUserPost(
            UserPost(
                id = userPostsDatabase.getUserPostDao().getMinLocalUserPostId() - 1,
                userId = userId,
                title = title,
                body = body
            )
        )
    }

    private fun getAllLocalUserPosts(): List<UserPost> =
        userPostsDatabase.getUserPostDao().getAllUserPosts()

    fun getPostsAndUserInfo(): List<UserPostModel>? {
        return if (getAllLocalUserPosts().isEmpty()) {
            try {
                val listOfPosts = postsReposApi.getPostsList().execute().body()
                saveDataToLocal(listOfPosts)
                getAllLocalUserPosts().let(
                    postMapper::map
                )
            } catch (e: Exception) {
                null
            }
        } else {
            return getAllLocalUserPosts().let(
                postMapper::map
            )
        }
    }

    private fun saveDataToLocal(listOfPosts: List<UserPostResponse>?) {
        listOfPosts?.forEach { userPostResponse ->
            insertUserPostFromApi(
                UserPost(
                    id = userPostResponse.id + 1000,
                    title = userPostResponse.title,
                    body = userPostResponse.body,
                    userId = userPostResponse.userId
                )
            )

        }
    }

}